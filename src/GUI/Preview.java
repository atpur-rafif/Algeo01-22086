package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

class Preview extends JPanel {
    Component c;

    Preview() {
        var l = new JLabel("Output");
        add(l);
    }
}