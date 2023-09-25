package GUI;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.*;

public class MainApp{
    static int counter = 0;
    public static void Show(){
        var frame = new JFrame("Matrix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var label = new JLabel("Hello");
        var button = new JButton("Add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter += 1;
                label.setText("Count: " + counter);
                frame.pack();
            }
        });

        var panel = frame.getContentPane();
        var layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(label)
                .addComponent(button)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(label)
                    .addComponent(button)
                )
        );

        frame.pack();
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
