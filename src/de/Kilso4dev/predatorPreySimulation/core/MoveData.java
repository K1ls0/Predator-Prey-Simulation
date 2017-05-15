package de.Kilso4dev.predatorPreySimulation.core;

import java.awt.*;

public class MoveData {
    public Dimension coordinates;

    public long  iteration,
                predAmount,
                preyAmount;


    public String happening;


    public MoveData() {
        happening = new String();
        iteration = 0;
        preyAmount = 0;
        predAmount = 0;
    }
    public MoveData(Dimension coordinates, int predatorAmount, int preyAmount, String happening, int iteration) {
        this.coordinates = coordinates;

        this.predAmount = predatorAmount;
        this.preyAmount = preyAmount;

        this.happening = happening;

        this.iteration = iteration;
    }
}
