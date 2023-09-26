package GUI;


import javax.swing.*;

import GUI.InputMatrix.*;
import Matrix.MatrixPrinter;
import Matrix.MatrixReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class JInputMatrix extends JPanel{
    public MatrixInputField[] inputTypes = new MatrixInputField[]{new Basic(), new CLI(), (MatrixInputField) new File()};

    public JPanel inputPanel;
    public JPanel previewPanel;
    public JComboBox<MatrixInputField> inputTypeSelection;

    void parseAndShowResult(){
        try {
            var s = new Scanner("");
            var m = MatrixReader.read(s);
            MatrixPrinter.print(m);
        } catch (Exception err) {
            String msg = "";
            if (err instanceof NumberFormatException) {
                String cause = err.getMessage().replace("For input string: ", "");
                msg = "Invalid string: " + cause ;
            } else if (err instanceof NoSuchElementException) {
                msg = "Invalid format";
            } else {
                msg = "Unknown error";
            }
            System.out.println(msg);
        }
    }

    JInputMatrix(){
        inputPanel = new JPanel();
        previewPanel = new Preview();
        inputTypeSelection = new JComboBox<MatrixInputField>(inputTypes);

        var layout = new SpringLayout();
        setLayout(layout);

        add(previewPanel);
        add(inputPanel);
    }
}
