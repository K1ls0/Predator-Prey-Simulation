package de.Kilso4dev.predatorPreySimulation.core;

class Predator implements Animal {
    @Override
    public String getType() {
        return "Predator";
    }

    @Override
    public int getAmount() {
        return 1;
    }
}
