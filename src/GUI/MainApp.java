package GUI;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridLayout;

public class MainApp{
    public static void Show(){
        var frame = new JFrame("Matrix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var matrix = new JInputMatrix();
        var matrix2 = new JInputMatrix();

        var panel = frame.getContentPane();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(matrix);
        panel.add(matrix2);

        frame.setMinimumSize(new Dimension(400, 600));
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void Run(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                MainApp.Show();
            }
        });
   }
}
