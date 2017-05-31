package de.Kilso4dev.predatorPreySimulation;

import java.awt.*;
import java.text.DecimalFormat;

public final class SimulationConstants {

    //Version
    public static final String VERSION = "v. 0.8";

    //not in use
    public static final String INFO_TEXT = "Dies ist ein Testsatz!\nUnd ich versuche, mit den Zeilen klar zu kommen";

    //Constants that define the values associated with Predators/ Preys/ no animal
    public static final byte    PREDATOR = 1,
                                PREY = 2,
                                NO_ANIMAL = -1;


    //amount of the animals needed for the analyses (diagram)
    public static final int PREDATOR_AMOUNT = 1,
                            PREY_AMOUNT = 2;


    //Number format needed for the Output in the TextArea
    public static DecimalFormat PART_OUTPUT_FORMAT = null;

    //Font needed for all Components of the Tool
    public static final Font fAll = new Font("Times New Roman", Font.PLAIN, 17);



}
