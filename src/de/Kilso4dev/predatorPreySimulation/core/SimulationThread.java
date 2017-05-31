package de.Kilso4dev.predatorPreySimulation.core;

import de.Kilso4dev.predatorPreySimulation.core.events.Coordinates;
import de.Kilso4dev.predatorPreySimulation.core.events.SimulationEvent;
import de.Kilso4dev.predatorPreySimulation.core.events.SimulationFinishedListener;
import de.Kilso4dev.predatorPreySimulation.window.FrameCore;

import java.util.LinkedList;
import java.util.List;

import static de.Kilso4dev.predatorPreySimulation.SimulationConstants.PREDATOR;
import static de.Kilso4dev.predatorPreySimulation.SimulationConstants.NO_ANIMAL;
import static de.Kilso4dev.predatorPreySimulation.SimulationConstants.PREY;

public class SimulationThread extends Thread {
    private FrameCore coreReference;
    private int generations;

    private byte[][] playingField;
    private List<MoveData> targetData;

    private List<SimulationFinishedListener> listener;


    public SimulationThread(FrameCore reference, byte[][] playingField, int generations, List<MoveData> targetData) {
        setDaemon(true);

        this.coreReference = reference;
        this.generations = generations;
        this.playingField = playingField;
        this.targetData = targetData;
        this.listener = new LinkedList<>();
    }


    public void run() {

        for (int i = 1; i <= generations; i++) {
            //round for Predator
            predatorsMove(i);

            //round for Prey
            preysMove(i);
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

        coreReference.addTextAreaEntry(md, generations);

        targetData.add(md);
    }

    private void preysMove(int iteration) {
        MoveData md = new MoveData();

        int x = (int) (Math.random() * playingField.length);
        int y = (int) (Math.random() * playingField[0].length);

        md.coordinates = new Coordinates(x, y);
        md.iteration = iteration;

        if (playingField[x][y] == NO_ANIMAL) {
            playingField[x][y] = PREY;

        } else if (playingField[x][y] == PREY) {
            Coordinates d = getNextFreeArea(new Coordinates(x, y));
            if (d != null) {
                playingField[d.getX()][d.getY()] = PREY;
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

    //TODO need a better algorithm to get the next free coordinates
    private Coordinates getNextFreeArea(Coordinates currentCoordinates) {
        for (int x = currentCoordinates.getX(); x < playingField.length; x++) {
            for (int y = currentCoordinates.getY(); y < playingField[x].length; y++) {
                if (playingField[x][y] == NO_ANIMAL) {
                    return new Coordinates(x, y);
                }
            }
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


    public void addSimulationFinishedListener(SimulationFinishedListener l) {
        listener.add(l);
    }
}
