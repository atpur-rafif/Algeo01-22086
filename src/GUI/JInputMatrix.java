package GUI;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import GUI.InputMatrix.Basic;
import GUI.InputMatrix.CLI;
import GUI.InputMatrix.File;
import GUI.InputMatrix.InputPanel;
import GUI.InputMatrix.MatrixInputField;
import Matrix.Matrix;
import Matrix.MatrixPrinter;
import Matrix.MatrixReader;

public class JInputMatrix extends JPanel{
    private Matrix currentValue;
    public MatrixInputField[] inputTypes = new MatrixInputField[]{new Basic(), new CLI(), new File()};

    public InputPanel inputPanel;
    public JComboBox<MatrixInputField> inputTypeSelection;
    public JLabel titleLabel;
    public JLabel errorLabel;

    void setError(String msg){ errorLabel.setText(" (" + msg + ")"); refresh(); }
    void clearError(){ errorLabel.setText(""); refresh(); }

    void parseAndShowResult(String text){
        clearError();
        try {
            var s = new Scanner(text);
            var m = MatrixReader.read(s);
            MatrixPrinter.print(m);
            this.currentValue = m;
        } catch (Exception err) {
            setError("Parse Error");
            this.currentValue = new Matrix(0, 0);
        }
    }

    Matrix getValue(){
        return this.currentValue;
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
        titleLabel = new JLabel("Matrix 1");
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        inputPanel = new InputPanel();
        inputTypeSelection = new JComboBox<MatrixInputField>(inputTypes);
        for(int i = 0; i < inputTypeSelection.getItemCount(); ++i) inputTypeSelection.getItemAt(i).addPropertyChangeListener("input", propertyChangeListener);

        parseAndShowResult("");
        inputPanel.changeComponent(getSelectedInputType());
        inputTypeSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.changeComponent(getSelectedInputType());
            }
        });

        var layout = new SpringLayout();
        setLayout(layout);

        var pad = 10;

        add(titleLabel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, pad, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, -pad, SpringLayout.HORIZONTAL_CENTER, this);

        add(errorLabel);
        layout.putConstraint(SpringLayout.NORTH, errorLabel, 0, SpringLayout.SOUTH, titleLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, errorLabel, -pad, SpringLayout.HORIZONTAL_CENTER, this);

        add(inputTypeSelection);
        layout.putConstraint(SpringLayout.NORTH, inputTypeSelection, 0, SpringLayout.SOUTH, errorLabel);
        layout.putConstraint(SpringLayout.WEST, inputTypeSelection, pad, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, inputTypeSelection, -pad, SpringLayout.EAST, this);

        add(inputPanel);
        layout.putConstraint(SpringLayout.WEST, inputPanel, pad, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, inputPanel, -pad, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, inputPanel, 0, SpringLayout.SOUTH, inputTypeSelection);
        layout.putConstraint(SpringLayout.SOUTH, inputPanel, -pad, SpringLayout.SOUTH, this);
    }
}
