package GUI.InputMatrix;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CLI extends JPanel implements MatrixInputField {
    Component component = new JLabel("CLI");

    @Override
    public String toString() {
        return "CLI";
    }

    @Override
    public String getValue() {
        return "CLI duls";
    }
}