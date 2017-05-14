package de.Kilso4dev.predatorPreySimulation.core;

import de.Kilso4dev.predatorPreySimulation.core.events.SimulationEvent;
import de.Kilso4dev.predatorPreySimulation.core.events.SimulationFinishedListener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SimulationThread extends Thread {
    private int generations;

    private Animal[][] playingField;
    private List<MoveData> targetData;

    private List<SimulationFinishedListener> listener;


    public SimulationThread(Animal[][] playingField, int generations, List<MoveData> targetData) {
        this.generations = generations;
        this.playingField = playingField;
        this.targetData = targetData;
        this.listener = new LinkedList<>();
    }


    public void run() {

        for (int i = 0; i < generations; i++) {
            //round for Predator
            predatorsMove(i);
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
        md.foundAnimal = playingField[x][y];

        if ((playingField[x][y] == null) || (playingField[x][y] instanceof Predator)) {
            deleteNextPredator();
        } else if (playingField[x][y] instanceof Prey) {
            playingField[x][y] = new Predator();
        }

        md.changedAnimal = playingField[x][y];

        targetData.add(md);
    }

    private void preysMove(int iteration) {
        MoveData md = new MoveData();

        int x = (int) (Math.random() * playingField.length);
        int y = (int) (Math.random() * playingField[iteration].length);

        md.coordinates = new Dimension(x, y);
        md.foundAnimal = playingField[x][y];

        if (playingField[x][y] == null) {
            playingField[x][y] = new Prey();

            md.changedAnimal = playingField[x][y];

        } else if (playingField[x][y] instanceof Prey) {
            Dimension d = getNextFreeArea(new Dimension(x, y));
            playingField[(int)d.getWidth()][(int)d.getHeight()] = new Prey();

            md.coordinates = d;
            md.changedAnimal = playingField[(int)d.getWidth()][(int)d.getHeight()];
        }

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



    public void addSimulationFinishedListener(SimulationFinishedListener l) {
        listener.add(l);
    }
}
