package de.Kilso4dev.predatorPreySimulation.core;

import de.Kilso4dev.predatorPreySimulation.core.events.Coordinates;
import de.Kilso4dev.predatorPreySimulation.window.FrameCore;

import java.awt.*;
import java.text.DecimalFormat;

public class MoveData {
    public Coordinates coordinates;

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



    public MoveData(Coordinates coordinates, long predatorAmount, long preyAmount, String happening, long currentIteration) {
        this.coordinates = coordinates;

        this.predAmount = predatorAmount;
        this.preyAmount = preyAmount;

        this.happening = happening;

        this.iteration = currentIteration;
    }
}
