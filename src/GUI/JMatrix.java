package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Matrix.Matrix;

public class JMatrix extends JPanel{
    public JMatrix(Matrix m){
        var c = new GridBagConstraints();
        setLayout(new GridBagLayout());

        for(int i = 0; i < m.row; ++i){
            for(int j = 0; j < m.col; ++j){
                c.gridx = j;
                c.gridy = i;
                c.fill = GridBagConstraints.CENTER;
                c.ipadx = 20;
                c.ipady = 20;

                var text = Double.toString(m.get(i, j));
                var comp = new JLabel(text);
                add(comp, c);
            }
        }
   }
}
