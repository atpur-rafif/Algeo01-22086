package GUI;


import javax.swing.*;
import javax.swing.event.*;

import Matrix.MatrixPrinter;
import Matrix.MatrixReader;

import java.awt.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

enum InputType{
    Basic,
    CLI,
    File
}

abstract class MatrixInputComponent{
    public abstract String toString();
    public abstract Component getComponent();
    public abstract String getValue();
}

class Basic extends MatrixInputComponent{
    Component component = new JLabel("Basic");

    @Override
    public String toString() {
        return "Basic";
    }

    @Override
    public Component getComponent() {
        return this.component;
    }

    @Override
    public String getValue() {
        return "Basic";
    }
}

class CLI extends MatrixInputComponent{
    Component component = new JLabel("CLI");

    @Override
    public String toString() {
        return "CLI";
    }

    @Override
    public Component getComponent() {
        return this.component;
    }

    @Override
    public String getValue() {
        return "CLI";
    }
}

class File extends MatrixInputComponent{
    Component component = new JLabel("File");

    @Override
    public String toString() {
        return "File";
    }

    @Override
    public Component getComponent() {
        return this.component;
    }

    @Override
    public String getValue() {
        return "File";
    }
}

public class JInputMatrix extends JPanel{
    public MatrixInputComponent[] inputTypes = new MatrixInputComponent[]{new Basic(), new CLI(), new File()};

    public JTextArea textArea = new JTextArea(10, 30);
    public JComboBox<MatrixInputComponent> inputType = new JComboBox<MatrixInputComponent>(inputTypes);
    public JLabel output = new JLabel("Output");

    void parseAndShowResult(){
        try {
            var s = new Scanner(textArea.getText());
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
        var titleLabel = new JLabel("Matrix Input");

        var layout = new GroupLayout(this);
        this.setLayout(layout);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) { update(e); }
			@Override
			public void removeUpdate(DocumentEvent e) { update(e); }
			@Override
			public void changedUpdate(DocumentEvent e) { update(e); }

            protected void update(DocumentEvent e){
                parseAndShowResult();
                SwingUtilities.getWindowAncestor(textArea).pack();
            }
        });

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titleLabel)
                .addComponent(inputType)
                .addComponent(textArea)
                .addComponent(output)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addComponent(inputType)
                .addComponent(textArea)
                .addComponent(output)
        );
    }
}
