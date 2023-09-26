package GUI.InputMatrix;

import javax.swing.JLabel;

public class Basic extends MatrixInputField {

    public Basic(){
        add(new JLabel("Basic"));
    }

    @Override
    public String toString() {
        return "Basic";
    }

    @Override
    public String getValue() {
        return "Basic duls";
    }
}