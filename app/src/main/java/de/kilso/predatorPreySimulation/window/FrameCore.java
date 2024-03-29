package de.kilso.predatorPreySimulation.window;

import de.kilso.predatorPreySimulation.SimulationConstants;
import de.kilso.predatorPreySimulation.core.DiagramData;
import de.kilso.predatorPreySimulation.core.MoveData;
import de.kilso.predatorPreySimulation.core.SimulationCore;
import de.kilso.predatorPreySimulation.core.events.SimulationEvent;
import de.kilso.predatorPreySimulation.core.events.SimulationFinishedListener;
import de.kilso.predatorPreySimulation.window.components.JDiagramPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


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

    private JDiagramPanel diagram;

    public FrameCore() {
        cMainWindow = getContentPane();
        cMainWindow.setLayout(null);
        createHeadline();
        createBottomLine();
        createOutput();
        createDiagram();
        //createBeta();
    }

    private void createBeta() {
        JLabel betaInfo = new JLabel("Beta - Work in progress");
        betaInfo.setBounds(550, 225, 250, 49);
        betaInfo.setVisible(true);
        betaInfo.setFont(SimulationConstants.fAll);
        cMainWindow.add(betaInfo);
    }

    private void createBottomLine() {
        stopButton = new JButton("Abbruch");
        stopButton.setBounds(540, 620, 120, 30);
        stopButton.setVisible(true);
        stopButton.setEnabled(false);
        stopButton.setFont(SimulationConstants.fAll);
        stopButton.setActionCommand("bCancelPressed");
        stopButton.addActionListener(new ButtonListener(this));
        cMainWindow.add(stopButton);

        startButton = new JButton("Start");
        startButton.setBounds(670, 620, 120, 30);
        startButton.setVisible(true);
        startButton.setFont(SimulationConstants.fAll);
        startButton.setActionCommand("bStartPressed");
        startButton.addActionListener(new ButtonListener(this));
        cMainWindow.add(startButton);

        closeButton = new JButton("Schließen");
        closeButton.setBounds(800, 620, 120, 30);
        closeButton.setVisible(true);
        closeButton.setFont(SimulationConstants.fAll);
        closeButton.setActionCommand("bClosePressed");
        closeButton.addActionListener(new ButtonListener(this));
        cMainWindow.add(closeButton);

    }

    private void createOutput() {
        partOutput = new JTextArea();
        partOutput.setBounds(90, 200, 300, 350);
        partOutput.setWrapStyleWord(true);
        partOutput.setLineWrap(true);
        partOutput.setFont(new Font(SimulationConstants.fAll.getFontName(), SimulationConstants.fAll.getStyle(), 15));
        partOutput.setEditable(false);

        partOutputPane = new JScrollPane(partOutput);
        partOutputPane.setBounds(90, 200, 300, 350);
        partOutputPane.setBorder(new LineBorder(new Color(50,50,50)));

        cMainWindow.add(partOutputPane);


        predPreyOutputPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        predPreyOutputPanel.setBounds(90, 570, 300, 30);


        predOutput = new JTextField();
        predOutput.setFont(SimulationConstants.fAll);
        predOutput.setEditable(false);
        predPreyOutputPanel.add(predOutput);


        preyOutput = new JTextField();
        preyOutput.setFont(SimulationConstants.fAll);
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
        animalInfo.setFont(SimulationConstants.fAll);
        headlineMenu.add(animalInfo);

        JLabel preyInfo = new JLabel("Beute:");
        preyInfo.setForeground(new Color(0, 110,0));
        preyInfo.setBounds(600, 59, 60, 30);
        preyInfo.setVisible(true);
        preyInfo.setFont(SimulationConstants.fAll);
        headlineMenu.add(preyInfo);

        JLabel predatorInfo = new JLabel("Räuber:");
        predatorInfo.setForeground(new Color(110, 0, 0));
        predatorInfo.setBounds(600, 89, 60, 30);
        predatorInfo.setVisible(true);
        predatorInfo.setFont(SimulationConstants.fAll);
        headlineMenu.add(predatorInfo);

        preyInput = new JSpinner(new SpinnerNumberModel(8, 1, Integer.MAX_VALUE, 1));
        preyInput.setBounds(665, 59, 90, 30);
        preyInput.setVisible(true);
        preyInput.setFont(SimulationConstants.fAll);
        preyInput.addChangeListener(new InputSpinnerChanged(this));
        headlineMenu.add(preyInput);

        predatorInput = new JSpinner(new SpinnerNumberModel(8, 1, Integer.MAX_VALUE, 1));
        predatorInput.setBounds(665, 89, 90, 30);
        predatorInput.setVisible(true);
        predatorInput.setFont(SimulationConstants.fAll);
        predatorInput.addChangeListener(new InputSpinnerChanged(this));
        headlineMenu.add(predatorInput);

        animalsRandom = new JButton("Default");
        animalsRandom.setBounds(770, 59, 150, 60);
        animalsRandom.setVisible(true);
        animalsRandom.setFont(SimulationConstants.fAll);
        animalsRandom.setActionCommand("bDefaultPreysPredatorsPressed");
        animalsRandom.addActionListener(new ButtonListener(this));
        headlineMenu.add(animalsRandom);
    }

    private void createFieldSize() {
        JLabel fieldInfo = new JLabel("Spielfeldgröße (quadratisch):");
        fieldInfo.setBounds(300, 5, 250, 49);
        fieldInfo.setVisible(true);
        fieldInfo.setFont(SimulationConstants.fAll);
        headlineMenu.add(fieldInfo);

        fieldInput = new JSpinner(new SpinnerNumberModel(6, 1, Integer.MAX_VALUE, 1));
        fieldInput.setBounds(300, 59, 80, 30);
        fieldInput.setVisible(true);
        fieldInput.setFont(SimulationConstants.fAll);
        fieldInput.addChangeListener(new InputSpinnerChanged(this));
        headlineMenu.add(fieldInput);

        fieldRandom = new JButton("Default");
        fieldRandom.setBounds(390, 59, 150, 30);
        fieldRandom.setVisible(true);
        fieldRandom.setFont(SimulationConstants.fAll);
        fieldRandom.setActionCommand("bDefaultFieldPressed");
        fieldRandom.addActionListener(new ButtonListener(this));
        headlineMenu.add(fieldRandom);
    }

    private void createGenerations() {
        JLabel generationInfo = new JLabel("Anzahl der Generationen:");
        generationInfo.setBounds(20, 5, 250, 49);
        generationInfo.setVisible(true);
        generationInfo.setFont(SimulationConstants.fAll);
        headlineMenu.add(generationInfo);

        generationInput = new JSpinner(new SpinnerNumberModel(100, 1, Integer.MAX_VALUE, 10));
        generationInput.setBounds(20, 59, 80, 30);
        generationInput.setVisible(true);
        generationInput.setFont(SimulationConstants.fAll);
        headlineMenu.add(generationInput);

        generationRandom = new JButton("Default");
        generationRandom.setBounds(110, 59, 150, 30);
        generationRandom.setVisible(true);
        generationRandom.setFont(SimulationConstants.fAll);
        generationRandom.setActionCommand("bDefaultAmountPressed");
        generationRandom.addActionListener(new ButtonListener(this));
        headlineMenu.add(generationRandom);
    }

    private void createDiagram() {
        diagram = new JDiagramPanel();
        diagram.setBounds(400, 200, 500, 400);
        diagram.setFont(SimulationConstants.fAll);
        diagram.setVisible(false);

        cMainWindow.add(diagram);
    }


    /**
     * A method that resets Components when Starting a new Simulation
     * @return nothing
     */
    private void resetOutput() {

        partOutput.setText(null);
        predOutput.setText(null);
        preyOutput.setText(null);


        diagram.setDiagram(false);

        SimulationConstants.PART_OUTPUT_FORMAT = null;
    }

    public void addTextAreaEntry(MoveData data, long maxGenerations) {

        String iteration = formatByNumberLength(maxGenerations, data.iteration++);
        partOutput.append(iteration + ". Gen:     Predators: " + (data.predAmount * SimulationConstants.PREDATOR_AMOUNT) + "       Preys: " + (data.preyAmount * SimulationConstants.PREY_AMOUNT)+ "\n");
    }


    private String formatByNumberLength(int lengthDigit, int i) {
        return formatByNumberLength((long) lengthDigit, (long) i);
    }

    private String formatByNumberLength(long lengthDigit, long l) {

        if (SimulationConstants.PART_OUTPUT_FORMAT == null) {
            StringBuffer bufferedFormat = new StringBuffer();


            for (int iteration = 0; iteration < String.valueOf(lengthDigit).length(); iteration++) {
                bufferedFormat.append('0');
            }
            SimulationConstants.PART_OUTPUT_FORMAT = new DecimalFormat(bufferedFormat.toString());
        }

        return SimulationConstants.PART_OUTPUT_FORMAT.format(l);

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
                //adding Listener that fires after Simulation is finished
                simulationCore.startSimulation(referenceFrame, new PredPreySimulationFinishedListener(this));

            } else if (e.getActionCommand().equals("bDefaultPreysPredatorsPressed")) {

                int fieldSideLen = (int)fieldInput.getValue();
                int fields = fieldSideLen * fieldSideLen;
                int predatorDef = fields/3;

                preyInput.setValue(predatorDef);
                predatorInput.setValue(predatorDef);

            } else if (e.getActionCommand().equals("bDefaultFieldPressed")) {
                int allEntities = (int)predatorInput.getValue() + (int)preyInput.getValue();

                int neededFieldLen = (int) Math.ceil(Math.sqrt(allEntities));

                fieldInput.setValue(neededFieldLen);

            } else if (e.getActionCommand().equals("bDefaultAmountPressed")) {
                generationInput.setValue(2000);
            }
        }

        void change(boolean b) {
            startButton.setEnabled(b);
            stopButton.setEnabled(!b);
            for (Component cComponent : headlineMenu.getComponents()) {
                cComponent.setEnabled(b);
            }
        }
    }


    /**
     * Helping Class for redirecting of Spinner Change Events to the ActionPerformed Events
     */
    private class InputSpinnerChanged implements ChangeListener {

        private FrameCore ref;

        private InputSpinnerChanged(FrameCore reference) {
            ref = reference;
        }

        @Override
        public void stateChanged(ChangeEvent e) {

            long    square = ((Integer) fieldInput.getValue()) * ((Integer) fieldInput.getValue()),
                    inputAmount = ((Integer) predatorInput.getValue()) + ((Integer) preyInput.getValue());


            if (e.getSource().equals(fieldInput) && inputAmount > square) {
                new ButtonListener(ref).actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "bRandomPreysPredatorsPressed"));
            } else if ((e.getSource().equals(predatorInput) || e.getSource().equals(preyInput)) && inputAmount > square) {
                new ButtonListener(ref).actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "bRandomFieldPressed"));
            }

        }
    }

    private class PredPreySimulationFinishedListener implements  SimulationFinishedListener {
        private ButtonListener reference;

        public PredPreySimulationFinishedListener(ButtonListener ref) {
            reference = ref;
        }


        @Override
        public void SimulationFinished(SimulationEvent e) {
            reference.change(true);
            java.util.List<MoveData> data = e.getSource().getMoveData();

            MoveData lastData = data.get(data.size()-1);


            predOutput.setText("Predators: " + (lastData.predAmount * SimulationConstants.PREDATOR_AMOUNT));
            preyOutput.setText("Preys: " + (lastData.preyAmount * SimulationConstants.PREY_AMOUNT));

            partOutput.setCaretPosition(partOutput.getDocument().getLength());
            diagram.setDiagramData(new DiagramData(data.toArray(new MoveData[data.size()])));
            diagram.setDiagram(true);
            }

    }
}
