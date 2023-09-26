package GUI.InputMatrix;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class File extends MatrixInputField {
    String oldValue = "";
    JLabel filePath;
    JButton dialogButton;

    public File() {
        filePath = new JLabel();
        dialogButton = new JButton("Select File");

        dialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDialog();
            }
        });

        add(filePath);
        add(dialogButton);
    }

    public void startDialog(){
        JFileChooser fileDialog = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "txt");
        fileDialog.setFileFilter(filter);
        int returnVal = fileDialog.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            var file = fileDialog.getSelectedFile();
            var path = file.toPath();
            try {
				var content = Files.readString(path);
                filePath.setText(file.getName());
                var newValue = content;
                notifyChange(fileDialog, oldValue, newValue);
                oldValue = newValue;
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

    @Override
    public String toString() {
        return "File";
    }

    @Override
    public String getValue() {
        return "File duls";
    }
}