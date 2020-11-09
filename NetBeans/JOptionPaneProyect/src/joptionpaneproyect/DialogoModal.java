/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joptionpaneproyect;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author axegas
 */
public class DialogoModal extends JDialog {

    private JTextField textField;

    public DialogoModal(JFrame parent) {
        super(parent, true);
        setTitle("Enter a data");
        textField = new JTextField(20);
        add(textField);

        // The window is hidden when pressing <enter> on the textfield
        textField.addActionListener(t -> setVisible(false));
    }

    public String getText() {
        return textField.getText();
    }

}
