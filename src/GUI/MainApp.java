package GUI;

import javax.swing.*;

public class MainApp{
    public static void Show(){
        var frame = new JFrame("Matrix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var matrix = new JInputMatrix();
        frame.getContentPane().add(matrix);

        frame.setSize(400, 300);
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
