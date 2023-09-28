package GUI.InputMatrix;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Basic extends MatrixInputField {
    private int maxSize = 5;
    public JPanel gridInput;
    public Integer[] dimensionOption = new Integer[maxSize];
    public JComboBox<Integer> rowInput;
    public JComboBox<Integer> colInput;

    private int getRow(){
        return (int) rowInput.getSelectedItem();
    }

    private int getCol(){
        return (int) colInput.getSelectedItem();
    }

    private void dimensionHandler(){
        gridInput.removeAll();
        gridInput.setLayout(new GridLayout(getRow(), getCol()));
        for(int i = 0; i < getRow(); ++i){
            for(int j = 0; j < getCol(); ++j){
                var textField = new JTextField();
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.getDocument().addDocumentListener(documentListener);
                gridInput.add(textField);
            }
        }
        gridInput.revalidate();
        gridInput.repaint();
    }

    private ActionListener dimensionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dimensionHandler();
        }
    };

    private DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) { update(e); }
        @Override
        public void removeUpdate(DocumentEvent e) { update(e); }
        @Override
        public void changedUpdate(DocumentEvent e) { update(e); }
        
        private void update(DocumentEvent e){
            notifyChange(gridInput, "TOOL_TIP_TEXT_KEY", getValue());
        }
    };

    public Basic(){
        for(int i = 0; i < maxSize; ++i) dimensionOption[i] = i + 1;

        var layout = new SpringLayout();
        setLayout(layout);
        
        gridInput = new JPanel();

        rowInput = new JComboBox<Integer>(dimensionOption.clone());
        rowInput.addActionListener(dimensionListener);
        colInput = new JComboBox<Integer>(dimensionOption.clone());
        colInput.addActionListener(dimensionListener);
        dimensionHandler();

        add(rowInput);
        layout.putConstraint(SpringLayout.EAST, rowInput, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, rowInput, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, rowInput, 0, SpringLayout.NORTH, this);

        add(colInput);
        layout.putConstraint(SpringLayout.WEST, colInput, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.EAST, colInput, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, colInput, 0, SpringLayout.NORTH, this);

        add(gridInput);
        layout.putConstraint(SpringLayout.WEST, gridInput, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, gridInput, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, gridInput, 0, SpringLayout.SOUTH, rowInput);
        layout.putConstraint(SpringLayout.SOUTH, gridInput, 0, SpringLayout.SOUTH, this);
    }

    @Override
    public String toString() {
        return "Basic";
    }

    @Override
    public String getValue() {
        String r = getRow() + " " + getCol() + "\n";
        for(var c : gridInput.getComponents()){
            if(c instanceof JTextField){
                r += ((JTextField) c).getText() + " ";
            }
        }
        return r;
    }
}