package de.Kilso4dev.predatorPreySimulation.core;

public class SimulationCore {

    Animal[][] playingField;


    public SimulationCore(int gridX, int gridY, int predatores, int preys) {
        playingField = new Animal[gridX][gridY];

    }
}
