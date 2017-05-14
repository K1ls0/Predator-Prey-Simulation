package de.Kilso4dev.predatorPreySimulation.core;

import javax.swing.*;
import java.util.List;

public class SimulationThread extends Thread {
    private int generations;

    private Animal[][] playingField;
    private List<MoveData> targetData;


    public SimulationThread(Animal[][] playingField, int generations, List<MoveData> targetData) {
        this.generations = generations;
        this.playingField = playingField;
        this.targetData = targetData;
    }


    public void run() {

        for (int i = 0; i < generations; i++) {
            //round for Predator
            predatorsMove(i);
            preysMove(i);
        }
    }



    private void predatorsMove(int iteration) {


        int x = (int) (Math.random() * playingField.length);
        int y = (int) (Math.random() * playingField[iteration].length);

        if ((playingField[x][y] == null) || (playingField[x][y] instanceof Predator)) {

        } else if (playingField[x][y] instanceof Prey) {

        }
    }

    private void preysMove(int iteration) {


        int x = (int) (Math.random() * playingField.length);
        int y = (int) (Math.random() * playingField[iteration].length);

        if ((playingField[x][y] == null) || (playingField[x][y] instanceof Predator)) {
            deleteNextPredator();
        } else if (playingField[x][y] instanceof Prey) {

        }
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

    private void deleteNextPrey() {
        for (int x = 0; x < playingField.length; x++) {
            for (int y = 0; y < playingField[x].length; y++) {
                if (playingField[x][y] instanceof Prey) {
                    playingField[x][y] = null;
                    return;
                }
            }
        }
    }
}
