package de.Kilso4dev.predatorPreySimulation.core;

import de.Kilso4dev.predatorPreySimulation.core.events.SimulationEvent;
import de.Kilso4dev.predatorPreySimulation.core.events.SimulationFinishedListener;
import de.Kilso4dev.predatorPreySimulation.window.FrameCore;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SimulationThread extends Thread {
    private FrameCore coreReference;
    private int generations;

    private Animal[][] playingField;
    private List<MoveData> targetData;

    private List<SimulationFinishedListener> listener;


    public SimulationThread(FrameCore reference, Animal[][] playingField, int generations, List<MoveData> targetData) {
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
        int y = (int) (Math.random() * playingField[iteration].length);

        md.coordinates = new Dimension(x, y);
        md.iteration = iteration;

        if ((playingField[x][y] == null) || (playingField[x][y] instanceof Predator)) {
            deleteNextPredator();
        } else if (playingField[x][y] instanceof Prey) {
            playingField[x][y] = new Predator();
        }

        md.predAmount = getPredatorAmount();
        md.preyAmount = getPreyAmount();

        coreReference.addTextAreaEntry(md);

        targetData.add(md);
    }

    private void preysMove(int iteration) {
        MoveData md = new MoveData();

        int x = (int) (Math.random() * playingField.length);
        int y = (int) (Math.random() * playingField[iteration].length);

        md.coordinates = new Dimension(x, y);
        md.iteration = iteration;

        if (playingField[x][y] == null) {
            playingField[x][y] = new Prey();

        } else if (playingField[x][y] instanceof Prey) {
            Dimension d = getNextFreeArea(new Dimension(x, y));
            playingField[(int)d.getWidth()][(int)d.getHeight()] = new Prey();

            md.coordinates = d;
        }

        md.predAmount = getPredatorAmount();
        md.preyAmount = getPreyAmount();

        targetData.add(md);
    }



    private void deleteNextPredator() {
        for (int x = 0; x < playingField.length; x++) {
            for (int y = 0; y < playingField[x].length; y++) {
                if (playingField[x][y] instanceof Predator) {
                    playingField[x][y] = null;
                    return;
                }
            }
        }
    }

    //TODO need a better algorithm to get the next free coordinates
    private Dimension getNextFreeArea(Dimension currentCoordinates) {
        for (int x = (int) currentCoordinates.getWidth(); x < playingField.length; x++) {
            for (int y = (int) currentCoordinates.getHeight(); y < playingField[x].length; y++) {
                if (playingField[x][y] == null) {
                    return new Dimension(x, y);
                }
            }
        }
        return null;
    }

    private long getPredatorAmount() {
        long iterator = 0;

        for (Animal[] cAnimal1 : playingField) {
            for (Animal cAnimal2 : cAnimal1) {
                if (cAnimal2 != null && cAnimal2 instanceof Predator) {
                    iterator++;
                }
            }
        }
        return iterator;
    }

    private long getPreyAmount() {
        long iterator = 0;

        for (Animal[] cAnimal1 : playingField) {
            for (Animal cAnimal2 : cAnimal1) {
                if (cAnimal2 != null && cAnimal2 instanceof Prey) {
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
