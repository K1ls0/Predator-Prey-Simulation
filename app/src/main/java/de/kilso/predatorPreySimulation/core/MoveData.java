package de.kilso.predatorPreySimulation.core;

import de.kilso.predatorPreySimulation.core.events.Coordinates;

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
