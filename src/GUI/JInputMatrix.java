package GUI;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;

import Matrix.MatrixPrinter;
import Matrix.MatrixReader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.Scanner;

abstract class MatrixInputField{
    public abstract String toString();
    public abstract Component getComponent();
    public abstract String getValue();
}

class Basic extends MatrixInputField{
    Component component = new JLabel("Basic");

    @Override
    public String toString() {
        return "Basic";
    }

    @Override
    public Component getComponent() {
        return component;
    }

    @Override
    public String getValue() {
        return "Basic duls";
    }
}

class CLI extends MatrixInputField{
    Component component = new JLabel("CLI");

    @Override
    public String toString() {
        return "CLI";
    }

    @Override
    public Component getComponent() {
        return component;
    }

    @Override
    public String getValue() {
        return "CLI duls";
    }
}

class File extends MatrixInputField{
    Component component = new JLabel("File");

    @Override
    public String toString() {
        return "File";
    }

    @Override
    public Component getComponent() {
        return component;
    }

    @Override
    public String getValue() {
        return "File duls";
    }
}

public class JInputMatrix extends JPanel{
    public MatrixInputField[] inputTypes = new MatrixInputField[]{new Basic(), new CLI(), new File()};

    public JComboBox<MatrixInputField> inputType = new JComboBox<MatrixInputField>(inputTypes);
    public JPanel inputPanel = new JPanel();
    public JLabel output = new JLabel("Output");

    MatrixInputField getSelectedInputField(){
        return (MatrixInputField) inputType.getSelectedItem();
    }

    void repack(){
        var frame = SwingUtilities.getWindowAncestor(this);
        if(frame != null) frame.pack();
    }

    void refreshInputType(){
        var c = getSelectedInputField().getComponent();
        inputPanel.removeAll();
        inputPanel.add(c);
        inputPanel.revalidate();
        inputPanel.repaint();
        repack();
    }

    void parseAndShowResult(){
        try {
            var s = new Scanner(getSelectedInputField().getValue());
            var m = MatrixReader.read(s);
            MatrixPrinter.print(m);
            output.setForeground(Color.BLACK);
            output.setText(m.toString());
        } catch (Exception err) {
            String msg = "";
            if (err instanceof NumberFormatException) {
                String cause = err.getMessage().replace("For input string: ", "");
                msg = "Invalid string: " + cause ;
            } else if (err instanceof NoSuchElementException) {
                msg = "Invalid format";
            } else {
                msg = "Unknown error";
            }
            output.setForeground(Color.RED);
            output.setText(msg);
        }
    }

    JInputMatrix(){
        refreshInputType();
        inputType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { refreshInputType(); }
        });

        Border blackline = BorderFactory.createLineBorder(Color.black);
        inputPanel.setBorder(blackline);

    }
}
