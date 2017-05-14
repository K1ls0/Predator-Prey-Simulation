package de.Kilso4dev.predatorPreySimulation.core;

import java.util.LinkedList;
import java.util.List;

public class SimulationCore {

    Animal[][] playingField;

    SimulationThread simulation;
    List<MoveData> moveData;

    int generations;

    public SimulationCore(int gridX, int gridY, int predators, int preys, int generations) {
        playingField = new Animal[gridX][gridY];
        this.generations = generations;
        this.moveData = new LinkedList<>();

        //initialize predators
        for (int i = 0; i < predators; i++) {
            playingField[(int) (Math.random() * playingField.length)][(int) (Math.random() * playingField[0].length)] = new Predator();
        }

        int x;
        int y;

        //initialize Preys
        for (int i = 0; i < preys; i++) {
            do {
                x = (int) (Math.random() * playingField.length);
                y = (int) (Math.random() * playingField[0].length);
            } while (playingField[x][y] instanceof Predator);

            playingField[x][y] = new Prey();
        }
    }

    public void startSimulation() {
        simulation = new SimulationThread(playingField, generations, moveData);
    }

    public void interrupSimulation() {

    }
}
