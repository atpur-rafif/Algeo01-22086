package GUI;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Window{
    public void Show(){
        var frame = new JFrame();
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.add(new JLabel("Hello, world"));

        frame.setMinimumSize(new Dimension(200, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void Run(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                (new Window()).Show();
            }
        });
   }
}
