package de.Kilso4dev.predatorPreySimulation.core;

class Prey implements Animal {
    @Override
    public String getType() {
        return "Prey";
    }

    @Override
    public int getAmount() {
        return 2;
    }
}
