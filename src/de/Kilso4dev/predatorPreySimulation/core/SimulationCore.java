package de.Kilso4dev.predatorPreySimulation.core;

import de.Kilso4dev.predatorPreySimulation.core.events.Coordinates;
import de.Kilso4dev.predatorPreySimulation.core.events.SimulationFinishedListener;
import de.Kilso4dev.predatorPreySimulation.window.FrameCore;

import java.util.LinkedList;
import java.util.List;

public class SimulationCore {

    private Animal[][] playingField;


    private SimulationThread simulation;

    private List<MoveData> moveData;

    int generations;

    public SimulationCore(int gridX, int gridY, int predators, int preys, int generations) {
        this.playingField = new Animal[gridX][gridY];
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


    private void initializePredators(Animal[][] playingField, int amount) {
        int x, y;

        //initialize Predators
        for (int i = 0; i < amount; i++) {

            do {
                x = (int) (Math.random() * playingField.length);
                y = (int) (Math.random() * playingField.length);
            } while (playingField[x][y] != null);

            playingField[x][y] = new Predator();
        }
    }

    private void initializePreys(Animal[][] playingField, int amount) {
        int x, y;

        //initialize Preys
        for (int i = 0; i < amount; i++) {
            do {
                x = (int) (Math.random() * playingField.length);
                y = (int) (Math.random() * playingField[0].length);
            } while (playingField[x][y] != null);

            playingField[x][y] = new Prey();
        }
    }
}