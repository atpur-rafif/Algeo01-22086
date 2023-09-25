package GUI;

import javax.swing.*;

import java.awt.event.*;

public class MainApp{
    static int resetCounter = 0;
    public static void Show(){
        var frame = new JFrame("Matrix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var label = new JLabel("Reset Counter: 0");
        var button = new JButton("Reset");
        var textArea = new JTextArea(10, 10);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetCounter += 1;
                label.setText("Reset Counter: " + resetCounter);
                textArea.setText("");
                frame.pack();
            }
        });

        var panel = frame.getContentPane();
        var layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(label)
                    .addComponent(button)
                )
                .addComponent(textArea)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(label)
                    .addComponent(button)
                )
                .addComponent(textArea)
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
