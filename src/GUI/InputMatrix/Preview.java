package GUI.InputMatrix;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Preview extends JPanel {
    Component c;

    public Preview() {
        setLayout(new GridBagLayout());
        add(new JLabel("Preview"));
    }
}