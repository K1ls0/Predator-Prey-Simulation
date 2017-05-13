package de.Kilso4dev.predatorPreySimulation.window;

import de.Kilso4dev.predatorPreySimulation.SimulationConstants;
import de.Kilso4dev.predatorPreySimulation.core.SimulationCore;

import javax.swing.*;
import java.awt.*;

public class FrameCore extends JFrame {

    private Container cMainWindow;

    private JLabel infoLabel;

    //size-choosing components
    private JPanel fieldChoosingPanel;
    private JSpinner    chooseXCoordinate,
                        chooseYCoordinate;


    public FrameCore() {
        cMainWindow = getContentPane();
        cMainWindow.setLayout(new GridBagLayout());

        setupInfoLabel();
        setupGridChoosing();
    }

    public void setupInfoLabel() {
        infoLabel = new JLabel(SimulationConstants.INFO_TEXT);

        //setup constraints for Label
        GridBagConstraints constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = 0;
        constr.gridheight = 2;
        constr.gridwidth = 1;
        constr.anchor = GridBagConstraints.FIRST_LINE_END;
        constr.insets = new Insets(20,20,20,20);
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.ipadx = 0;
        constr.ipady = 0;

        //add Label to Container
        cMainWindow.add(infoLabel, constr);
    }

    public void setupGridChoosing() {

        //initialize Panel
        fieldChoosingPanel = new JPanel(new GridLayout(1,3));


        //setup JSpinner
        chooseXCoordinate = new JSpinner(new SpinnerNumberModel(10, 0, Integer.MAX_VALUE, 1));
        chooseYCoordinate = new JSpinner(new SpinnerNumberModel(10, 0, Integer.MAX_VALUE, 1));

        //setup "X"-JLabel
        JLabel xLabel = new JLabel("X");
        xLabel.setHorizontalAlignment(JLabel.CENTER);

        //add components to Panel
        fieldChoosingPanel.add(chooseXCoordinate);
        fieldChoosingPanel.add(xLabel);
        fieldChoosingPanel.add(chooseYCoordinate);

        //setup constraints for Panel
        GridBagConstraints constr = new GridBagConstraints();
        constr.gridx = 3;
        constr.gridy = 0;
        constr.gridheight = 1;
        constr.gridwidth = 1;
        constr.anchor = GridBagConstraints.FIRST_LINE_END;
        constr.insets = new Insets(0,0,0,0);
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.ipadx = 20;
        constr.ipady = 0;

        cMainWindow.add(fieldChoosingPanel, constr);
    }


}
