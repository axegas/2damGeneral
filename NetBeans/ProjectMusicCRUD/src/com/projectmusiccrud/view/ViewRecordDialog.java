/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.view;

import com.projectmusiccrud.model.Record;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author peixe
 */
public class ViewRecordDialog extends JDialog {

    private JTextField txtName, txtComposer, txtYear;
    private JLabel lblName, lblComposer, lblYear;
    private JButton btnAccept, btnCancel;
    private JCheckBox cbListened;

    private Record record;

    public ViewRecordDialog(JFrame parent, Record r, String str) {
        super(parent, str + " a record", true);
        this.record = r;
        initComponents();
    }

    public Record getRecord() {
        return record;
    }

    private void initComponents() {

        setLayout(new GridLayout(5, 2, 10, 10));
        setBounds(400, 400, 200, 200);

        txtName = new JTextField(20);
        txtComposer = new JTextField(20);
        txtYear = new JTextField(15);

        txtName.setText(record.getName());
        txtComposer.setText(record.getComposer());
        txtYear.setText(Integer.toString(record.getYear()));

        lblName = new JLabel("Name: ");
        lblComposer = new JLabel("Composer: ");
        lblYear = new JLabel("Year: ");

        btnAccept = new JButton("Accept");
        btnCancel = new JButton("Cancel");

        cbListened = new JCheckBox("Listened?");
        
        cbListened.setSelected(record.isListened());

        add(lblName);
        add(txtName);
        add(lblComposer);
        add(txtComposer);
        add(lblYear);
        add(txtYear);
        add(cbListened);
        add(new JLabel());
        add(btnAccept);
        add(btnCancel);

        btnCancel.addActionListener(c -> cancel());
        btnAccept.addActionListener(a -> accept());
    }

    private void accept() {
        String name = txtName.getText();
        String composer = txtComposer.getText();
        int year = Integer.parseInt(txtYear.getText());
        boolean listened = cbListened.isSelected();
        record.setName(name);
        record.setComposer(composer);
        record.setYear(year);
        record.setListened(listened);
        setVisible(false);
    }

    private void cancel() {
        record = null;
        setVisible(false);
    }
}
