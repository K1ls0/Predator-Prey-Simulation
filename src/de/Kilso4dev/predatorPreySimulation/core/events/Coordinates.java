package de.Kilso4dev.predatorPreySimulation.core.events;

public class Coordinates {
    private int     x,
                    y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}