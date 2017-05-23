package de.Kilso4dev.predatorPreySimulation.core;

public class DiagramData {

    private int dataLength;
    private long[]  predatorAmount,
                    preyAmount;


    public DiagramData(MoveData[] data) {
        dataLength = data.length;

        predatorAmount = new long[data.length];
        preyAmount = new long[data.length];

        //initialize predatorAmount & preyAmount
        for (int i = 0; i < data.length; i++) {
            predatorAmount[i] = data[i].predAmount;

            preyAmount[i] = data[i].preyAmount;
        }
    }


    public int getDataLength() {
        return dataLength;
    }


    //getter of the value preyAmount
    public long[] getPredatorAmount() {
        return predatorAmount;
    }
    public long getPredatorAmount(int index) {
        return predatorAmount[index];
    }


    //getter of the value preyAmount
    public long[] getPreyAmount() {
        return preyAmount;
    }
    public long getPreyAmount(int index) {
        return preyAmount[index];
    }
}
