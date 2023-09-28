package GUI.InputMatrix;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class InputPanel extends JPanel{
    public InputPanel(){
        setLayout(new GridLayout(1, 1));
    }

    public void changeComponent(Component c){
        removeAll();
        add(c);
        revalidate();
        repaint();
    }
}