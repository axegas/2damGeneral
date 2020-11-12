/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.view;

import com.projectmusiccrud.controller.ControllerRecord;
import com.projectmusiccrud.model.Record;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author peixe
 */
public class ViewRecordMain extends JFrame {

    private JTable table;
    private JPanel pnlTop, pnlBottom, pnlMain;
    private JMenuBar mnBar;
    private JButton btnUpdate, btnDelete;
    private final ControllerRecord controller;

    public ViewRecordMain(ControllerRecord controller) {
        this.controller = controller;
        initComponents();
        configMenuBar();
        reload();
        setColumnPrefWidth();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 200, 500, 400);
        setTitle("Record table");

        mnBar = new JMenuBar();
        table = new JTable();

        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        btnUpdate.addActionListener(b -> update());
        btnDelete.addActionListener(b -> delete());

        pnlMain = new JPanel(new BorderLayout());
        pnlTop = new JPanel(new BorderLayout());
        pnlBottom = new JPanel(new FlowLayout());

        pnlTop.add(mnBar, BorderLayout.NORTH);
        pnlTop.add(new JScrollPane(table), BorderLayout.CENTER);
        pnlBottom.add(btnUpdate);
        pnlBottom.add(btnDelete);

        add(pnlMain);
        pnlMain.add(pnlTop, BorderLayout.CENTER);
        pnlMain.add(pnlBottom, BorderLayout.SOUTH);
        
        JTextArea t = new JTextArea();
        

    }

    private void configMenuBar() {
        JMenu menuOptions = new JMenu("Options");
        mnBar.add(menuOptions);
        JMenuItem menuItem = new JMenuItem("New");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuOptions.add(menuItem);
        menuItem.addActionListener(t -> insert());

        menuItem = new JMenuItem("Delete All");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        menuOptions.add(menuItem);
        menuItem.addActionListener(t -> deleteAll());

        menuOptions.addSeparator();

        menuItem = new JMenuItem("Print");
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        menuOptions.add(menuItem);
        menuItem.addActionListener(t -> print());
    }

    private void setColumnPrefWidth() {
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(170);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(30);
    }

    private Record getSelectedValue(int n) {
        Record r = null;
        if (n >= 0) {
            TableModel model = table.getModel();
            int id = (Integer) model.getValueAt(n, 0);
            String name = (String) model.getValueAt(n, 1);
            String composer = (String) model.getValueAt(n, 2);
            int year = (Integer) model.getValueAt(n, 3);
            r = new Record(id, name, composer, year);
        }
        return r;
    }

    private void reload() {
        table.setModel(controller.selectModel());
    }

    private void insert() {
        Record r = new Record();
        ViewRecordDialog modalDialog = new ViewRecordDialog(this, r, "Insert");
        modalDialog.pack();
        modalDialog.setVisible(true);
        r = modalDialog.getRecord();
        if (r != null) {
            controller.insert(r);
            reload();
        }
    }

    private void update() {
        Record r = getSelectedValue(table.getSelectedRow());
        if (r == null) {
            JOptionPane.showMessageDialog(null, "No record selected", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            ViewRecordDialog modalDialog = new ViewRecordDialog(this, r, "Update");
            modalDialog.pack();
            modalDialog.setVisible(true);
            r = modalDialog.getRecord();
            if (r != null) {
                controller.update(r);
                reload();
            }
        }
    }

    private void delete() {
        Record r = getSelectedValue(table.getSelectedRow());
        if (r == null) {
            JOptionPane.showMessageDialog(null, "No record selected", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int opc = JOptionPane.showOptionDialog(null, "Are you sure?", "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, "Info");
            if (opc == 0) {
                controller.delete(r);
                reload();
            }

        }
    }

    private void deleteAll() {
        int opc = JOptionPane.showOptionDialog(null, "Are you sure?", "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, "Info");
        if (opc == 0) {
            for (int i = 0; i < table.getModel().getRowCount(); i++) {
                Record r = getSelectedValue(i);
                controller.delete(r);
            }
            reload();
        }

    }

    private void print() {
        ArrayList<Record> records = new ArrayList<>();
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            Record r = getSelectedValue(i);
            records.add(r);
        }
        controller.createPDF(records);
    }

}
