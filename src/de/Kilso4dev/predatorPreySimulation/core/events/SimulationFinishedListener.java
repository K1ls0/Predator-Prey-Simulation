package de.Kilso4dev.predatorPreySimulation.core.events;

import java.util.EventListener;

public interface SimulationFinishedListener extends EventListener{

    void SimulationFinished(SimulationEvent e);

}
