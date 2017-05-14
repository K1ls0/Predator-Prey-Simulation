package de.Kilso4dev.predatorPreySimulation.core;

public class SimulationThread extends Thread {
    private int generations;

    private Animal[][] playingField;

    public SimulationThread(Animal[][] playingField, int generations) {
        this.generations = generations;
        this.playingField = playingField;
    }


    public void run() {

    }
}
