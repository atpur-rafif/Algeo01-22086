package GUI;


import javax.swing.*;

import GUI.InputMatrix.*;
import Matrix.MatrixReader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class JInputMatrix extends JPanel{
    public MatrixInputField[] inputTypes = new MatrixInputField[]{new Basic(), new CLI(), new File()};

    public InputPanel inputPanel;
    public JComboBox<MatrixInputField> inputTypeSelection;
    public JLabel titleLabel;
    public JLabel errorLabel;

    void setError(String msg){ errorLabel.setText(" (" + msg + ")"); refresh(); }
    void clearError(){ errorLabel.setText(""); refresh(); }

    void parseAndShowResult(String text){
        try {
            var s = new Scanner(text);
            var m = MatrixReader.read(s);
            clearError();
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
            setError(msg);
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

    void refresh(){
        revalidate();
        repaint();
    }

    JInputMatrix(){
        inputPanel = new InputPanel();
        inputTypeSelection = new JComboBox<MatrixInputField>(inputTypes);
        for(int i = 0; i < inputTypeSelection.getItemCount(); ++i) inputTypeSelection.getItemAt(i).addPropertyChangeListener("input", propertyChangeListener);

        inputPanel.changeComponent(getSelectedInputType());
        inputTypeSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.changeComponent(getSelectedInputType());
            }
        });

        var layout = new SpringLayout();
        setLayout(layout);

        titleLabel = new JLabel("Matrix 1");
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);

        var pad = 10;

        add(titleLabel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, pad, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, -pad, SpringLayout.HORIZONTAL_CENTER, this);

        add(inputTypeSelection);
        layout.putConstraint(SpringLayout.NORTH, inputTypeSelection, 0, SpringLayout.SOUTH, titleLabel);
        layout.putConstraint(SpringLayout.WEST, inputTypeSelection, pad, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, inputTypeSelection, -pad, SpringLayout.EAST, this);

        add(inputPanel);
        layout.putConstraint(SpringLayout.WEST, inputPanel, pad, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, inputPanel, -pad, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, inputPanel, 0, SpringLayout.SOUTH, inputTypeSelection);
        layout.putConstraint(SpringLayout.SOUTH, inputPanel, -pad, SpringLayout.SOUTH, this);
    }
}
