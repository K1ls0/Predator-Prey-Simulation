package de.Kilso4dev.predatorPreySimulation.core.events;

import de.Kilso4dev.predatorPreySimulation.core.SimulationCore;
import de.Kilso4dev.predatorPreySimulation.core.SimulationThread;

public class SimulationEvent {

    private SimulationThread source;


    public SimulationEvent(SimulationThread reference) {
        this.source = reference;
    }


    public SimulationThread getSource() {
        return source;
    }
}
