package GUI;


import javax.swing.*;

import GUI.InputMatrix.*;
import Matrix.MatrixReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class JInputMatrix extends JPanel{
    public MatrixInputField[] inputTypes = new MatrixInputField[]{new Basic(), new CLI(), new File()};

    public Input inputPanel;
    public Preview previewPanel;
    public JComboBox<MatrixInputField> inputTypeSelection;

    void parseAndShowResult(String text){
        try {
            var s = new Scanner(text);
            var m = MatrixReader.read(s);
            previewPanel.setMatrix(m);
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
            previewPanel.setError(msg);
        }
    }

    private MatrixInputField getSelectedInputType(){
        return (MatrixInputField) inputTypeSelection.getSelectedItem();
    }

    public PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
            String text = (String) evt.getNewValue();
            parseAndShowResult(text);
		}
    };

    JInputMatrix(){
        inputPanel = new Input();
        previewPanel = new Preview();
        inputTypeSelection = new JComboBox<MatrixInputField>(inputTypes);
        for(int i = 0; i < inputTypeSelection.getItemCount(); ++i) inputTypeSelection.getItemAt(i).addPropertyChangeListener("input", propertyChangeListener);

        inputTypeSelection.setSelectedIndex(1);
        inputPanel.changeComponent(getSelectedInputType());
        inputTypeSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.changeComponent(getSelectedInputType());
            }
        });

        var layout = new SpringLayout();
        setLayout(layout);

        var title = new JLabel("Matrix 1");

        add(title);
        layout.putConstraint(SpringLayout.NORTH, title, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);

        add(inputTypeSelection);
        layout.putConstraint(SpringLayout.NORTH, inputTypeSelection, 0, SpringLayout.SOUTH, title);
        layout.putConstraint(SpringLayout.WEST, inputTypeSelection, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, inputTypeSelection, 0, SpringLayout.EAST, this);

        add(inputPanel);
        layout.putConstraint(SpringLayout.WEST, inputPanel, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, inputPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, inputPanel, 0, SpringLayout.SOUTH, inputTypeSelection);
        layout.putConstraint(SpringLayout.SOUTH, inputPanel, 0, SpringLayout.SOUTH, this);

        add(previewPanel);
        layout.putConstraint(SpringLayout.WEST, previewPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.EAST, previewPanel, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, previewPanel, 0, SpringLayout.SOUTH, inputTypeSelection);
        layout.putConstraint(SpringLayout.SOUTH, previewPanel, 0, SpringLayout.SOUTH, this);
    }
}
