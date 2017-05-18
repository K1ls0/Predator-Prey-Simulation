package de.Kilso4dev.predatorPreySimulation.window.components;

import de.Kilso4dev.predatorPreySimulation.core.MoveData;

import javax.swing.*;
import java.awt.*;

public class JDiagramPanel extends JPanel {

    private boolean diagramEnabled = false;

    private DiagramData diagramData;

    private int horizontalStartPoint,
                verticalStartPoint,
                horizontalLineLength,
                verticalLineLength;


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
            //TODO not implemented yet




            //draw horizontal sections
            //TODO not implemented yet
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
        }
    }



    public void enableDiagram(boolean flag) {
        setVisible(flag);

        this.diagramEnabled = flag;
        repaint();
    }
}
