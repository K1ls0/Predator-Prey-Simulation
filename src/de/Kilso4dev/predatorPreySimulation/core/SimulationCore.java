package de.Kilso4dev.predatorPreySimulation.core;

import de.Kilso4dev.predatorPreySimulation.SimulationConstants;
import de.Kilso4dev.predatorPreySimulation.core.events.Coordinates;
import de.Kilso4dev.predatorPreySimulation.core.events.SimulationFinishedListener;
import de.Kilso4dev.predatorPreySimulation.window.FrameCore;

import java.util.LinkedList;
import java.util.List;

import static de.Kilso4dev.predatorPreySimulation.SimulationConstants.NO_ANIMAL;
import static de.Kilso4dev.predatorPreySimulation.SimulationConstants.PREDATOR;
import static de.Kilso4dev.predatorPreySimulation.SimulationConstants.PREY;

public class SimulationCore {

    private byte[][] playingField;


    private SimulationThread simulation;

    private List<MoveData> moveData;

    int generations;

    public SimulationCore(int gridX, int gridY, int predators, int preys, int generations) {

        //Initialize playingField
        this.playingField = new byte[gridX][gridY];

        for (int x = 0; x < playingField.length; x++) {
            for (int y = 0; y < playingField[x].length; y++) {
                playingField[x][y] = NO_ANIMAL;
            }
        }




        this.generations = generations;
        this.moveData = new LinkedList<>();


        while (predators + preys > gridX * gridY) {
            predators--;
            preys--;
        }

        initializePredators(playingField, predators);

        initializePreys(playingField, preys);

        moveData.add(new MoveData(new Coordinates(0, 0), predators, preys, "Initialization", 0));
    }

    public void startSimulation(FrameCore reference, SimulationFinishedListener l) {
        simulation = new SimulationThread(reference, playingField, generations, moveData);
        simulation.addSimulationFinishedListener(l);


        reference.addTextAreaEntry(moveData.get(0), generations);

        simulation.start();
    }

    public void interruptSimulation() {
        if (simulation != null && simulation.isAlive()) {
            simulation.interrupt();
        }
    }


    private List<MoveData> getMoveData() {
        return moveData;
    }


    private void initializePredators(byte[][] playingField, int amount) {
        int x, y;

        //initialize Predators
        for (int i = 0; i < amount; i++) {

            do {
                x = (int) (Math.random() * playingField.length);
                y = (int) (Math.random() * playingField.length);
            } while (playingField[x][y] != NO_ANIMAL);

            playingField[x][y] = PREDATOR;
        }
    }

    private void initializePreys(byte[][] playingField, int amount) {
        int x, y;

        //initialize Preys
        for (int i = 0; i < amount; i++) {
            do {
                x = (int) (Math.random() * playingField.length);
                y = (int) (Math.random() * playingField[0].length);
            } while (playingField[x][y] != NO_ANIMAL);

            playingField[x][y] = PREY;
        }
    }
}