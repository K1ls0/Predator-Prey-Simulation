package de.Kilso4dev.predatorPreySimulation.window.components;

import de.Kilso4dev.predatorPreySimulation.core.MoveData;

import javax.swing.*;
import java.awt.*;

public class JDiagramPanel extends JPanel {

    private boolean diagramEnabled = false;

    private DiagramData diagramData;

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

    }

    private void drawGridSections(Graphics g) {
        if (diagramData != null) {

        }
    }

    private void drawGraph(Graphics g) {
        if (diagramData != null) {

        }
    }



    public void enableDiagram(boolean flag) {
        setVisible(flag);

        this.diagramEnabled = flag;
        repaint();
    }
}
