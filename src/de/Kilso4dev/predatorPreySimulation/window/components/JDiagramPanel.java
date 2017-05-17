package de.Kilso4dev.predatorPreySimulation.window.components;

import de.Kilso4dev.predatorPreySimulation.core.MoveData;

import javax.swing.*;
import java.awt.*;

public class JDiagramPanel extends JLabel {

    private MoveData[] diagramData;

    public JDiagramPanel() {
        super();
        diagramData = new MoveData[0];
    }

    public JDiagramPanel(MoveData[] data) {
        super();
        this.diagramData = data;
    }

    @Override
    public void paintComponent(Graphics g) {
        drawGrid(g);
    }




    private void drawGrid(Graphics g) {
        //horizontal diagram line
        g.drawLine(15, getHeight() - 15, getWidth() - 15, getHeight() - 15);

        //vertical diagram line
        g.drawLine(15, getHeight() - 15, 15, 15);
    }
}
