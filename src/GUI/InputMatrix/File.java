package GUI.InputMatrix;

import javax.swing.JLabel;

public class File extends MatrixInputField {

    public File(){
        add(new JLabel("File"));
    }

    @Override
    public String toString() {
        return "File";
    }

    @Override
    public String getValue() {
        return "File duls";
    }
}