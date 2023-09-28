package GUI.InputMatrix;

import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

public abstract class MatrixInputField extends JPanel{
    public abstract String toString();
    public abstract String getValue();
    protected void notifyChange(Object target, String oldValue, String newValue){
        var listeners = getPropertyChangeListeners("input");
        for (int i = 0; i < listeners.length; ++i) {
            var listener = listeners[i];
            listener.propertyChange(new PropertyChangeEvent(target, "input", oldValue, newValue));
        }
    }
}