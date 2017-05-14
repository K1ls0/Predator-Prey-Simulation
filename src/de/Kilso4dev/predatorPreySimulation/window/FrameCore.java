package de.Kilso4dev.predatorPreySimulation.window;

import de.Kilso4dev.predatorPreySimulation.SimulationConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Add A Testarea with a JScrollPane named outputArea to display the output of every Run/ Generation
public class FrameCore extends JFrame {

    private Container cMainWindow;
    private JButton generationRandom;
    private JTextField generationInput;
    private JButton fieldRandom;
    private JTextField fieldInput;
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
        JLabel fieldInfo = new JLabel("Spielfeldgröße (quadratisch):");
        fieldInfo.setBounds(300, 5, 250, 49);
        fieldInfo.setVisible(true);
        fieldInfo.setFont(fAll);
        cMainWindow.add(fieldInfo);

        fieldInput = new JTextField("6");
        fieldInput.setBounds(300, 59, 60, 30);
        fieldInput.setVisible(true);
        fieldInput.setFont(fAll);
        cMainWindow.add(fieldInput);

        fieldRandom = new JButton("Zufällige Größe");
        fieldRandom.setBounds(370, 59, 150, 30);
        fieldRandom.setVisible(true);
        fieldRandom.setFont(fAll);
        fieldRandom.addActionListener(new ButtonListner());
        cMainWindow.add(fieldRandom);
    }

    private void createGenerations() {
        JLabel generationInfo = new JLabel("Anzahl der simulierten Generationen:");
        generationInfo.setBounds(20, 5, 250, 49);
        generationInfo.setVisible(true);
        generationInfo.setFont(fAll);
        cMainWindow.add(generationInfo);

        generationInput = new JTextField("100");
        generationInput.setBounds(20, 59, 60, 30);
        generationInput.setVisible(true);
        generationInput.setFont(fAll);
        cMainWindow.add(generationInput);

        generationRandom = new JButton("Zufällige Anzahl");
        generationRandom.setBounds(90, 59, 150, 30);
        generationRandom.setVisible(true);
        generationRandom.setFont(fAll);
        generationRandom.addActionListener(new ButtonListner());
        cMainWindow.add(generationRandom);
    }

    private class ButtonListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(generationRandom)) {

            }
        }
    }
}
