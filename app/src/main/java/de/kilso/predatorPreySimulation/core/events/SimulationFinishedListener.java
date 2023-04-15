package de.kilso.predatorPreySimulation.core.events;

import java.util.EventListener;

public interface SimulationFinishedListener extends EventListener{
    void SimulationFinished(SimulationEvent e);
}
