package de.kilso.predatorPreySimulation.core.events;

import de.kilso.predatorPreySimulation.core.SimulationCore;
import de.kilso.predatorPreySimulation.core.SimulationThread;

public class SimulationEvent {

    private SimulationThread source;


    public SimulationEvent(SimulationThread reference) {
        this.source = reference;
    }


    public SimulationThread getSource() {
        return source;
    }
}
