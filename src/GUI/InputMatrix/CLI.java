package GUI.InputMatrix;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CLI extends MatrixInputField {
    private String oldText = "";  
    JTextArea textArea;

    public CLI(){
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        add(textArea);

        textArea.setRows(10);
        textArea.setColumns(10);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { update(e); }
            @Override
            public void removeUpdate(DocumentEvent e) { update(e); }
            @Override
            public void changedUpdate(DocumentEvent e) { update(e); }

            private void update(DocumentEvent e){
                var listeners = getPropertyChangeListeners("input");
                var newText = getValue();
                for(int i = 0; i < listeners.length; ++i){
                    var listener = listeners[i];
                    listener.propertyChange(new PropertyChangeEvent(textArea, "input", oldText, newText));
                }
                oldText = newText;
            }
        });
    }

    @Override
    public String toString() {
        return "CLI";
    }

    @Override
    public String getValue() {
        return textArea.getText();
    }
}