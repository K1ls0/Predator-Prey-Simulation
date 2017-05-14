package de.Kilso4dev.predatorPreySimulation.core;

public class SimulationCore {

    Animal[][] playingField;


    public SimulationCore(int gridX, int gridY, int predatores, int preys) {
        playingField = new Animal[gridX][gridY];
        for (int i = 0; i < predatores; i++) {
            playingField[(int) (Math.random() * playingField.length)][(int) (Math.random() * playingField[0].length)] = new Predator();
        }

        int x;
        int y;

        for (int i = 0; i < preys; i++) {
            do {
                x = (int) (Math.random() * playingField.length);
                y = (int) (Math.random() * playingField[0].length);
            } while (playingField[x][y] instanceof Predator);
            playingField[x][y] = new Prey();
        }
    }
}
