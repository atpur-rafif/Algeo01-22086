package GUI.InputMatrix;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.JMatrix;
import Matrix.Matrix;

public class Preview extends JPanel {
    Component c;

    public Preview() {
        setLayout(new GridBagLayout());
        add(new JLabel("Preview"));
    }

    public void setError(String msg){
        replace(new JLabel(msg));
    }

    public void setMatrix(Matrix m){
        replace(new JMatrix(m));
    }

    private void replace(Component c){
        removeAll();
        add(c);
        revalidate();
        repaint();
    }
}