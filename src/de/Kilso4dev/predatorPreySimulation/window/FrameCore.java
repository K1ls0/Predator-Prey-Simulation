package de.Kilso4dev.predatorPreySimulation.window;

import de.Kilso4dev.predatorPreySimulation.SimulationConstants;
import de.Kilso4dev.predatorPreySimulation.core.MoveData;
import de.Kilso4dev.predatorPreySimulation.core.SimulationCore;
import de.Kilso4dev.predatorPreySimulation.core.events.SimulationEvent;
import de.Kilso4dev.predatorPreySimulation.core.events.SimulationFinishedListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

// TODO: Add A Textarea with a JScrollPane named outputArea to display the output of every Run/ Generation //ok
public class FrameCore extends JFrame {

    private SimulationCore simulationCore;
    private Container cMainWindow;
    private JPanel headlineMenu;
    private JButton generationRandom;
    private JButton fieldRandom;
    private JButton animalsRandom;
    private JButton stopButton;
    private JButton startButton;
    private JButton closeButton;
    private JSpinner    generationInput, fieldInput, preyInput, predatorInput;

    private JTextArea partOutput;
    private JScrollPane partOutputPane;
    private JPanel predPreyOutputPanel;
    private JTextField  predOutput,
                        preyOutput;

    private final Font fAll = new Font("Times New Roman", Font.PLAIN, 17);

    public FrameCore() {
        cMainWindow = getContentPane();
        cMainWindow.setLayout(null);
        createHeadline();
        createBottomLine();
        createOutput();
    }

    private void createBottomLine() {
        stopButton = new JButton("Abbruch");
        stopButton.setBounds(540, 620, 120, 30);
        stopButton.setVisible(true);
        stopButton.setEnabled(false);
        stopButton.setFont(fAll);
        stopButton.setActionCommand("bCancelPressed");
        stopButton.addActionListener(new ButtonListener(this));
        cMainWindow.add(stopButton);

        startButton = new JButton("Start");
        startButton.setBounds(670, 620, 120, 30);
        startButton.setVisible(true);
        startButton.setFont(fAll);
        startButton.setActionCommand("bStartPressed");
        startButton.addActionListener(new ButtonListener(this));
        cMainWindow.add(startButton);

        closeButton = new JButton("Schließen");
        closeButton.setBounds(800, 620, 120, 30);
        closeButton.setVisible(true);
        closeButton.setFont(fAll);
        closeButton.setActionCommand("bClosePressed");
        closeButton.addActionListener(new ButtonListener(this));
        cMainWindow.add(closeButton);

    }

    private void createOutput() {
        partOutput = new JTextArea();
        partOutput.setBounds(90, 200, 300, 350);
        partOutput.setWrapStyleWord(true);
        partOutput.setLineWrap(true);
        partOutput.setFont(new Font(fAll.getFontName(), fAll.getStyle(), 10));
        partOutput.setEditable(false);

        partOutputPane = new JScrollPane(partOutput);
        partOutputPane.setBounds(90, 200, 300, 350);
        partOutputPane.setBorder(new LineBorder(new Color(50,50,50)));

        cMainWindow.add(partOutputPane);


        predPreyOutputPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        predPreyOutputPanel.setBounds(90, 570, 300, 30);


        predOutput = new JTextField();
        predOutput.setFont(fAll);
        predOutput.setEditable(false);
        predPreyOutputPanel.add(predOutput);


        preyOutput = new JTextField();
        preyOutput.setFont(fAll);
        preyOutput.setEditable(false);
        predPreyOutputPanel.add(preyOutput);

        cMainWindow.add(predPreyOutputPanel);
    }

    private void createHeadline() {
        headlineMenu = new JPanel(null);
        headlineMenu.setBounds(0, 0, 950, 130);
        headlineMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        createGenerations();
        createFieldSize();
        createAnimals();
        headlineMenu.setVisible(true);
        cMainWindow.add(headlineMenu);
    }

    private void createAnimals() {
        JLabel animalInfo = new JLabel("Spielsteinauswahl:");
        animalInfo.setBounds(600, 5, 250, 49);
        animalInfo.setVisible(true);
        animalInfo.setFont(fAll);
        headlineMenu.add(animalInfo);

        JLabel preyInfo = new JLabel("Beute:");
        preyInfo.setBounds(600, 59, 60, 30);
        preyInfo.setVisible(true);
        preyInfo.setFont(fAll);
        headlineMenu.add(preyInfo);

        JLabel predatorInfo = new JLabel("Räuber:");
        predatorInfo.setBounds(600, 89, 60, 30);
        predatorInfo.setVisible(true);
        predatorInfo.setFont(fAll);
        headlineMenu.add(predatorInfo);

        preyInput = new JSpinner(new SpinnerNumberModel(8, 0, Integer.MAX_VALUE, 1));
        preyInput.setBounds(675, 59, 60, 30);
        preyInput.setVisible(true);
        preyInput.setFont(fAll);
        headlineMenu.add(preyInput);

        predatorInput = new JSpinner(new SpinnerNumberModel(8, 0, Integer.MAX_VALUE, 1));
        predatorInput.setBounds(675, 89, 60, 30);
        predatorInput.setVisible(true);
        predatorInput.setFont(fAll);
        headlineMenu.add(predatorInput);

        animalsRandom = new JButton("Zufällige Steine");
        animalsRandom.setBounds(750, 59, 150, 60);
        animalsRandom.setVisible(true);
        animalsRandom.setFont(fAll);
        animalsRandom.setActionCommand("bRandomPreysPredatorsPressed");
        animalsRandom.addActionListener(new ButtonListener(this));
        headlineMenu.add(animalsRandom);
    }

    private void createFieldSize() {
        JLabel fieldInfo = new JLabel("Spielfeldgröße (quadratisch):");
        fieldInfo.setBounds(300, 5, 250, 49);
        fieldInfo.setVisible(true);
        fieldInfo.setFont(fAll);
        headlineMenu.add(fieldInfo);

        fieldInput = new JSpinner(new SpinnerNumberModel(6, 0, Integer.MAX_VALUE, 1));
        fieldInput.setBounds(300, 59, 60, 30);
        fieldInput.setVisible(true);
        fieldInput.setFont(fAll);
        headlineMenu.add(fieldInput);

        fieldRandom = new JButton("Zufällige Größe");
        fieldRandom.setBounds(370, 59, 150, 30);
        fieldRandom.setVisible(true);
        fieldRandom.setFont(fAll);
        fieldRandom.setActionCommand("bRandomFieldPressed");
        fieldRandom.addActionListener(new ButtonListener(this));
        headlineMenu.add(fieldRandom);
    }

    private void createGenerations() {
        JLabel generationInfo = new JLabel("Anzahl der Generationen:");
        generationInfo.setBounds(20, 5, 250, 49);
        generationInfo.setVisible(true);
        generationInfo.setFont(fAll);
        headlineMenu.add(generationInfo);


        generationInput = new JSpinner(new SpinnerNumberModel(100, 0, Integer.MAX_VALUE, 10));
        generationInput.setBounds(20, 59, 60, 30);
        generationInput.setVisible(true);
        generationInput.setFont(fAll);
        headlineMenu.add(generationInput);

        generationRandom = new JButton("Zufällige Anzahl");
        generationRandom.setBounds(90, 59, 150, 30);
        generationRandom.setVisible(true);
        generationRandom.setFont(fAll);
        generationRandom.setActionCommand("bRandomAmountPressed");
        generationRandom.addActionListener(new ButtonListener(this));
        headlineMenu.add(generationRandom);
    }



    private class ButtonListener implements ActionListener {
        private FrameCore referenceFrame;

        ButtonListener(FrameCore ref) {
            this.referenceFrame = ref;
        }


        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals("bCancelPressed")) {
                if (simulationCore != null) {
                    simulationCore.interruptSimulation();
                    this.change(true);
                }



            } else if (e.getActionCommand().equals("bClosePressed")) {
                if (simulationCore != null) {
                    simulationCore.interruptSimulation();
                }
                dispose();




            } else if (e.getActionCommand().equals("bStartPressed")) {
                this.change(false);
                simulationCore = new SimulationCore((int) fieldInput.getValue(), (int) fieldInput.getValue(),
                        (int) predatorInput.getValue(), (int) preyInput.getValue(), (int) generationInput.getValue());

                resetOutput();

                simulationCore.startSimulation(referenceFrame, new SimulationFinishedListener() {
                    @Override
                    public void SimulationFinished(SimulationEvent e) {
                        change(true);
                        java.util.List<MoveData> data = e.getSource().getMoveData();

                        MoveData lastData = data.get(data.size()-1);

                        predOutput.setText("Predators: " + (lastData.predAmount * SimulationConstants.PREDATOR_AMOUNT));
                        preyOutput.setText("Preys: " + (lastData.preyAmount * SimulationConstants.PREY_AMOUNT));
                    }
                });



            } else if (e.getActionCommand().equals("bRandomPreysPredatorsPressed")) {
                int preyInputRandom;
                int predatorInputRandom;

                do {
                    preyInputRandom = (int)((Math.random() * 30000) + 20);
                    predatorInputRandom = (int)((Math.random() * 30000) + 20);
                } while ((preyInputRandom + predatorInputRandom) > ((int)fieldInput.getValue() * (int)fieldInput.getValue()));

                preyInput.setValue(preyInputRandom);
                predatorInput.setValue(predatorInputRandom);




            } else if (e.getActionCommand().equals("bRandomFieldPressed")) {

                int fieldInputRandom;

                do {
                    fieldInputRandom = (int) ((Math.random() * 200) + 5);
                } while (fieldInputRandom < ((int)(preyInput.getValue()) +  (int) predatorInput.getValue()));

                fieldInput.setValue(fieldInputRandom);


            } else if (e.getActionCommand().equals("bRandomAmountPressed")) {
                generationInput.setValue((int) (Math.random() * 4950) + 50);
            }
        }

        private void change(boolean b) {
            startButton.setEnabled(b);
            stopButton.setEnabled(!b);
            for (Component cComponent : headlineMenu.getComponents()) {
                cComponent.setEnabled(b);
            }
        }
    }


    private void resetOutput() {
        partOutput.setText(null);


        predOutput.setText(null);
        preyOutput.setText(null);
    }

    public void addTextAreaEntry(MoveData data) {
        partOutput.append(data.iteration++ + ". gen: Predators: " + (data.predAmount * SimulationConstants.PREDATOR_AMOUNT) + " || Preys: " + (data.preyAmount * SimulationConstants.PREY_AMOUNT)+ "\n");
    }
}
