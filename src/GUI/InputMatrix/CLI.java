package GUI.InputMatrix;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CLI extends MatrixInputField {

    public CLI(){
        add(new JLabel("CLI"));
    }

    @Override
    public String toString() {
        return "CLI";
    }

    @Override
    public String getValue() {
        return "CLI duls";
    }
}