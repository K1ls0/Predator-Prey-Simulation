package de.Kilso4dev.predatorPreySimulation.window.components;

import de.Kilso4dev.predatorPreySimulation.core.*;

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
            drawGridSections(g);
            drawGraph(g);
        } else {

        }
    }




    private void drawGrid(Graphics g) {
        //horizontal diagram line
        g.drawLine(15, getHeight() - 15, getWidth() - 15, getHeight() - 15);

        //vertical diagram line
        g.drawLine(15, getHeight() - 15, 15, 15);

        //arrow vertical
        int[]   xPoints = {10, 15, 20},
                yPoints = {20, 15, 20};

        g.drawPolyline(xPoints, yPoints, xPoints.length);


        //arrow horizontal
        xPoints = new int[] {getWidth() - 20, getWidth() - 15, getWidth() -20};
        yPoints = new int[] {getHeight() - 10, getHeight() - 15, getHeight() - 20};

        g.drawPolyline(xPoints, yPoints, xPoints.length);

        horizontalStartPoint = 15;
        verticalStartPoint = getHeight();

        horizontalLineLength = getWidth() - 30;
        verticalLineLength = getHeight() - 30;
    }

    private void drawGridSections(Graphics g) {
        if (diagramData != null) {
            long    maxData = getMaxData(diagramData.getPredatorAmount()) < getMaxData(diagramData.getPreyAmount()) ?
                    getMaxData(diagramData.getPreyAmount()) : getMaxData(diagramData.getPredatorAmount());

            //draw vertical sections
            //TODO not implemented fully yet
            setDataFactor(maxData, HORIZONTAL_LINE);



            //draw horizontal sections
            //TODO not implemented yet
            setDataFactor(diagramData.getDataLength(), VERTICAL_LINE);
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
            horizontalDataFactor = horizontalLineLength / maximumData;

        } else if (alignment == VERTICAL_LINE) {
            verticalDataFactor = verticalLineLength / maximumData;
        }
    }

    private long pixelLengthOf(long data, int alignment) {

        if (verticalDataFactor != NO_DATA_FACTOR && horizontalDataFactor != NO_DATA_FACTOR) {
            if (alignment == HORIZONTAL_LINE) {
                return (long) ((data * this.horizontalDataFactor) + horizontalStartPoint);
            } else if (alignment == VERTICAL_LINE) {
                return (long) ((data * this.verticalDataFactor) + verticalStartPoint);
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
                    dataX[iteration] =(int)(iteration * horizontalDataFactor);

                    dataYPredator[iteration] = (int) (diagramData.getPredatorAmount(iteration) * verticalDataFactor);
                    dataYPrey[iteration] = (int) (diagramData.getPreyAmount(iteration) * verticalDataFactor);
                }

                //draw predator Graph
                g.drawPolyline(dataX, dataYPredator, dataX.length);

                //draw prey Graph
                g.drawPolyline(dataX, dataYPrey, dataX.length);
            }
        }
    }



    public void enableDiagram(boolean flag) {
        setVisible(flag);

        this.diagramEnabled = flag;
        repaint();
    }
}
