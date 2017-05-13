package de.Kilso4dev.predatorPreySimulation.window;

import de.Kilso4dev.predatorPreySimulation.SimulationConstants;

import javax.swing.*;
import java.awt.*;

public class FrameCore extends JFrame {

    private Container cMainWindow;
    private final Font fAll = new Font("Times New Roman", Font.PLAIN, 15);

    public FrameCore() {
        cMainWindow = getContentPane();
        cMainWindow.setLayout(null);
        createHeadline();
    }

    private void createHeadline() {
        createGenerations();
        createFieldSize();
        createAnimals();
    }

    private void createAnimals() {
    }

    private void createFieldSize() {
    }

    private void createGenerations() {
        JLabel generationInfo = new JLabel("Anzahl der Generationen:");
        generationInfo.setBounds(20, 5, 200, 49);
        generationInfo.setVisible(true);
        generationInfo.setFont(fAll);
        cMainWindow.add(generationInfo);

        JTextField generationInput = new JTextField("100");
        generationInput.setBounds(20, 59, 150, 30);
        generationInput.setVisible(true);
        generationInput.setFont(fAll);
        cMainWindow.add(generationInput);

        JButton generationRandom = new JButton("Zufällige Anzahl");
        generationRandom.setBounds(20, 95, 150, 49);
        generationRandom.setVisible(true);
        generationRandom.setFont(fAll);
        cMainWindow.add(generationRandom);
    }
}
