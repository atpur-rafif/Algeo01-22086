package GUI.InputMatrix;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class File extends JPanel implements MatrixInputField {
    Component component = new JLabel("File");

    @Override
    public String toString() {
        return "File";
    }

    @Override
    public String getValue() {
        return "File duls";
    }
}