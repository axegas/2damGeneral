/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.view;

import com.projectmusiccrud.model.Record;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
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

        setLayout(new GridLayout(4, 2, 10, 10));
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

        add(lblName);
        add(txtName);
        add(lblComposer);
        add(txtComposer);
        add(lblYear);
        add(txtYear);
        add(btnAccept);
        add(btnCancel);

        btnCancel.addActionListener(c -> cancel());
        btnAccept.addActionListener(a -> accept());
    }

    private void accept() {
        String name = txtName.getText();
        String composer = txtComposer.getText();
        int year = Integer.parseInt(txtYear.getText());
        record.setName(name);
        record.setComposer(composer);
        record.setYear(year);
        setVisible(false);
    }

    private void cancel() {
        record = null;
        setVisible(false);
    }
}
