package de.kilso.predatorPreySimulation.core;

import de.kilso.predatorPreySimulation.core.events.Coordinates;
import de.kilso.predatorPreySimulation.core.events.SimulationEvent;
import de.kilso.predatorPreySimulation.core.events.SimulationFinishedListener;
import de.kilso.predatorPreySimulation.window.FrameCore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static de.kilso.predatorPreySimulation.SimulationConstants.PREDATOR;
import static de.kilso.predatorPreySimulation.SimulationConstants.NO_ANIMAL;
import static de.kilso.predatorPreySimulation.SimulationConstants.PREY;

public class SimulationThread extends Thread {
    private FrameCore coreReference;
    private int generations;
    private int report_iter;

    private byte[][] playingField;
    private List<MoveData> targetData;

    private List<SimulationFinishedListener> listener;

    private Boolean[][] iter_visited;
    private LinkedBlockingQueue<Coordinates> iter_q;


    public SimulationThread(FrameCore reference, byte[][] playingField, int generations, List<MoveData> targetData) {
        setDaemon(true);

        this.coreReference = reference;
        this.generations = generations;
        this.playingField = playingField;
        this.targetData = targetData;
        this.listener = new LinkedList<>();
        this.iter_q = new LinkedBlockingQueue<Coordinates>();
        if (playingField.length == 0) {
            this.iter_visited = new Boolean[0][0];
        } else {
            this.iter_visited = new Boolean[playingField.length][playingField[0].length];
        }
        this.report_iter = generations / 100;

    }


    public void run() {

        for (int i = 1; i <= generations; i++) {
            //round for Predator
            predatorsMove(i);

            //round for Prey
            try {
                preysMove(i);
            } catch(InterruptedException ie) {
                continue;
            }
        }

        for (SimulationFinishedListener cListener : listener) {
            cListener.SimulationFinished(new SimulationEvent(this));
        }
    }



    public List<MoveData> getMoveData() {
        return targetData;
    }


    private void predatorsMove(int iteration) {
        MoveData md = new MoveData();

        int x = (int) (Math.random() * playingField.length);
        int y = (int) (Math.random() * playingField[0].length);

        md.coordinates = new Coordinates(x, y);
        md.iteration = iteration;

        if ((playingField[x][y] == NO_ANIMAL) || (playingField[x][y] == PREDATOR)) {
            deleteNextPredator();
        } else if (playingField[x][y] == PREY) {
            playingField[x][y] = PREDATOR;
        }

        md.predAmount = getPredatorAmount();
        md.preyAmount = getPreyAmount();

        if (iteration % this.report_iter == 0) {
            coreReference.addTextAreaEntry(md, generations);
        }

        targetData.add(md);
    }

    private void preysMove(int iteration) throws InterruptedException {
        MoveData md = new MoveData();

        int x = (int) (Math.random() * playingField.length);
        int y = (int) (Math.random() * playingField[0].length);

        md.coordinates = new Coordinates(x, y);
        md.iteration = iteration;

        if (playingField[x][y] == NO_ANIMAL) {
            playingField[x][y] = PREY;

        } else if (playingField[x][y] == PREY) {
            Coordinates d = getNextFreeField(new Coordinates(x, y));
            if (d != null) {
                playingField[d.x][d.y] = PREY;
            }
            md.coordinates = d;
        }

        md.predAmount = getPredatorAmount();
        md.preyAmount = getPreyAmount();

        targetData.add(md);
    }



    private void deleteNextPredator() {
        for (int x = 0; x < playingField.length; x++) {
            for (int y = 0; y < playingField[x].length; y++) {
                if (playingField[x][y] == PREDATOR) {
                    playingField[x][y] = NO_ANIMAL;
                    return;
                }
            }
        }
    }

    private Coordinates getNextFreeField(Coordinates currentCoordinates) throws InterruptedException {
        this.resetVisited();
        iter_q.clear();

        iter_q.put(currentCoordinates);
        while (!iter_q.isEmpty()) {
            Coordinates c = iter_q.take();

            if (c == null) break;
            if (c.x < 0 || c.x >= iter_visited.length || c.y < 0 || c.y >= iter_visited[c.x].length) continue;
            if (iter_visited[c.x][c.y]) continue;

            iter_visited[c.x][c.y] = true;
            if (playingField[c.x][c.y] == NO_ANIMAL) {
                return c;
            }
            iter_q.put(new Coordinates(c.x-1, c.y-1));
            iter_q.put(new Coordinates(c.x, c.y-1));
            iter_q.put(new Coordinates(c.x+1, c.y-1));

            iter_q.put(new Coordinates(c.x-1, c.y));
            iter_q.put(new Coordinates(c.x+1, c.y));

            iter_q.put(new Coordinates(c.x-1, c.y+1));
            iter_q.put(new Coordinates(c.x, c.y+1));
            iter_q.put(new Coordinates(c.x+1, c.y+1));
        }

        return null;
    }

    private long getPredatorAmount() {
        long iterator = 0;

        for (byte[] cAnimal1dimensional : playingField) {
            for (byte cAnimal : cAnimal1dimensional) {
                if (cAnimal != NO_ANIMAL && cAnimal == PREDATOR) {
                    iterator++;
                }
            }
        }
        return iterator;
    }

    private long getPreyAmount() {
        long iterator = 0;

        for (byte[] cAnimal1dimensional : playingField) {
            for (byte cAnimal : cAnimal1dimensional) {
                if (cAnimal != NO_ANIMAL && cAnimal == PREY) {
                    iterator++;
                }
            }
        }
        return iterator;
    }

    private void resetVisited() {
        for (Boolean[] ccol: this.iter_visited) {
            Arrays.setAll(ccol, i -> false);
        }
    }

    public void addSimulationFinishedListener(SimulationFinishedListener l) {
        listener.add(l);
    }
}
