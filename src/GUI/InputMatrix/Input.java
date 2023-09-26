package GUI.InputMatrix;

import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class Input extends JPanel{
    public Input(){
        setLayout(new GridBagLayout());
    }

    public void changeComponent(Component c){
        removeAll();
        add(c);
        revalidate();
        repaint();
    }
}