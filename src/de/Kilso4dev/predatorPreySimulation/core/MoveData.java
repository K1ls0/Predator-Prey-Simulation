package de.Kilso4dev.predatorPreySimulation.core;

import java.awt.*;

public class MoveData {
    public Dimension coordinates;

    public Animal foundAnimal;
    public Animal changedAnimal;

    public String happening;


    public MoveData() {
        happening = new String();
    }
    public MoveData(Dimension coordinates, Animal foundAnimal, Animal changedAnimal, String happening) {
        this.coordinates = coordinates;

        this.foundAnimal = foundAnimal;
        this.changedAnimal = changedAnimal;

        this.happening = happening;
    }
}
