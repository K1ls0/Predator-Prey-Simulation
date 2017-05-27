package de.Kilso4dev.predatorPreySimulation.window.components;

import de.Kilso4dev.predatorPreySimulation.SimulationConstants;
import de.Kilso4dev.predatorPreySimulation.core.*;
import de.Kilso4dev.predatorPreySimulation.window.FrameCore;

import javax.swing.*;
import java.awt.*;

public class JDiagramPanel extends JPanel {

    private static final double NO_DATA_FACTOR = -1d;

    private static final int    VERTICAL_LINE = 1,
                                HORIZONTAL_LINE = 2;


    private boolean diagramEnabled = false;

    private DiagramData diagramData;

    private int horizontalStartPoint,
                verticalStartPoint,
                horizontalLineLength,
                verticalLineLength;

    private double  verticalDataFactor = NO_DATA_FACTOR,
                    horizontalDataFactor = NO_DATA_FACTOR;


    public JDiagramPanel() {
        super();
    }

    public JDiagramPanel(MoveData[] data) {
        super();
        this.diagramData = new DiagramData(data);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);

        if (diagramEnabled) {
            initializeFactors();
            drawGridSections(g);
            drawGraph(g);
        }
    }




    private void drawGrid(Graphics g) {
        g.setFont(SimulationConstants.fAll);


        //horizontal diagram line
        g.drawLine(15, getHeight() - 15, getWidth() - 15, getHeight() - 15);

        //vertical diagram line
        g.drawLine(15, getHeight() - 15, 15, 15);






        //horizontal label
        char[] drawChars = "Generations".toCharArray();
        g.drawChars(drawChars, 0, drawChars.length, getWidth() - 83, getHeight() - 25);

        //vertical label
        drawChars = "Animal Count".toCharArray();
        g.drawChars(drawChars, 0, drawChars.length, 20, 15);





        //arrow vertical
        int[]   xPoints = {10, 15, 20},
                yPoints = {20, 15, 20};

        g.drawPolyline(xPoints, yPoints, xPoints.length);

        //arrow horizontal
        xPoints = new int[] {getWidth() - 20, getWidth() - 15, getWidth() -20};
        yPoints = new int[] {getHeight() - 10, getHeight() - 15, getHeight() - 20};

        g.drawPolyline(xPoints, yPoints, xPoints.length);








        horizontalStartPoint = 15;
        verticalStartPoint = getHeight() - 15;

        horizontalLineLength = getWidth() - 30;
        verticalLineLength = getHeight() - 30;
    }

    private void drawGridSections(Graphics g) {
        if (diagramData != null) {
            //draw vertical sections
            //TODO not implemented fully yet





            //draw horizontal sections
            //TODO not implemented yet
        }
    }

    private void initializeFactors() {
        if (diagramData != null) {
            long maxData = getMaxData(diagramData.getPredatorAmount()) < getMaxData(diagramData.getPreyAmount()) ?
                    getMaxData(diagramData.getPreyAmount()) : getMaxData(diagramData.getPredatorAmount());

            setDataFactor(diagramData.getDataLength(), HORIZONTAL_LINE);


            setDataFactor(maxData, VERTICAL_LINE);
        }
    }



    private long getMaxData(long[] data) {
        long max = 0;
        for (long currentData : data) {
            if (max < currentData)
                max = currentData;
        }
        return max;
    }

    private void setDataFactor(long maximumData, int alignment) {
        if (alignment == HORIZONTAL_LINE) {
            horizontalDataFactor = (double) horizontalLineLength / (double) maximumData;

        } else if (alignment == VERTICAL_LINE) {
            verticalDataFactor = (double) verticalLineLength / (double) maximumData;
        }
    }

    private long pixelLengthOf(long data, int alignment) {

        if (verticalDataFactor != NO_DATA_FACTOR && horizontalDataFactor != NO_DATA_FACTOR) {
            if (alignment == HORIZONTAL_LINE) {

                return (long) (horizontalStartPoint + (data * this.horizontalDataFactor));
            } else if (alignment == VERTICAL_LINE) {

                return (long) (verticalStartPoint - (data * this.verticalDataFactor));
            }
        }

        return -1;
    }


    private void drawVerticalSectionLine(Graphics g, int x) {
        g.drawLine(x,getHeight() - 18, x, getHeight() - 12);
    }

    private void drawHorizontalSectionLine(Graphics g, int y) {
        g.drawLine(12, y, 18, y);
    }




    private void drawGraph(Graphics g) {
        if (diagramData != null) {
            //draw graph
            //TODO not implemented yet

            int[]   dataX = new int[diagramData.getDataLength()],
                    dataYPredator = new int[diagramData.getDataLength()],
                    dataYPrey = new int[diagramData.getDataLength()];

            if (verticalDataFactor != NO_DATA_FACTOR || horizontalDataFactor != NO_DATA_FACTOR) {

                for (int iteration = 0; iteration < diagramData.getDataLength(); iteration++) {

                    dataX[iteration] = (int) pixelLengthOf(iteration, HORIZONTAL_LINE);

                    dataYPredator[iteration] = (int) pixelLengthOf(diagramData.getPredatorAmount(iteration), VERTICAL_LINE);
                    dataYPrey[iteration] = (int) pixelLengthOf(diagramData.getPreyAmount(iteration), VERTICAL_LINE);


                }

                Color oldColor = g.getColor();

                g.setColor(new Color(110,0,0));
                //draw predator Graph
                g.drawPolyline(dataX, dataYPredator, dataX.length);

                g.setColor(new Color(0, 110,0));
                //draw prey Graph
                g.drawPolyline(dataX, dataYPrey, dataX.length);

                g.setColor(oldColor);
            }
        }
    }



    public void setDiagram(boolean flag) {
        setVisible(flag);

        if (!flag) {
            this.diagramData = null;
        }

        this.diagramEnabled = flag;
        repaint();
    }

    public void setDiagramData(DiagramData d) {
        this.diagramData = d;
    }
}