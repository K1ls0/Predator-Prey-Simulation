package de.Kilso4dev.predatorPreySimulation.core;

public class MoveData {
    public int gridX, gridY;

    public Animal foundAnimal;
    public  Animal changedAnimal;


    public MoveData() {}
    public MoveData(int gridX, int gridY, Animal foundAnimal, Animal changedAnimal) {
        this.gridX = gridX;
        this.gridY = gridY;

        this.foundAnimal = foundAnimal;
        this.changedAnimal = changedAnimal;
    }
}
