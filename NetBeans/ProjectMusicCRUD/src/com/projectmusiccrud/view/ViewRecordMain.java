/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.view;

import com.projectmusiccrud.controller.ControllerRecord;
import com.projectmusiccrud.model.Record;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
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
    private JTextField txtSearch;
    private JLabel lblSearch;
    private final ControllerRecord controller;

    public ViewRecordMain() {
        this.controller = new ControllerRecord();
        initComponents();
        configMenuBar();
        reload();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 300, 500, 400);
        setTitle("Record table");

        table = new JTable();
        mnBar = new JMenuBar();

        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        lblSearch = new JLabel("Search");
        txtSearch = new JTextField(20);

        txtSearch.addActionListener(t -> search());
        btnUpdate.addActionListener(b -> update());
        btnDelete.addActionListener(b -> delete());

        pnlMain = new JPanel(new BorderLayout());
        pnlTop = new JPanel(new BorderLayout());
        pnlBottom = new JPanel(new FlowLayout());        
               
        pnlTop.add(mnBar, BorderLayout.NORTH);
        pnlTop.add(new JScrollPane(table), BorderLayout.CENTER);
        pnlBottom.add(btnUpdate);
        pnlBottom.add(btnDelete);
        pnlBottom.add(lblSearch);
        pnlBottom.add(txtSearch);

        add(pnlMain);
        pnlMain.add(pnlTop, BorderLayout.CENTER);
        pnlMain.add(pnlBottom, BorderLayout.SOUTH);

    }

    private void configMenuBar() {
        JMenu menuOptions = new JMenu("Options");        
        JMenu menuYoutube = new JMenu("Other");
        
        mnBar.add(menuOptions);
        mnBar.add(menuYoutube);

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

        menuItem = new JMenuItem("Search in Youtube");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuYoutube.add(menuItem);
        menuItem.addActionListener(t -> youtube());

        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        menuYoutube.add(menuItem);
        menuItem.addActionListener(t -> exit());
    }

    private void reload() {
        table.setModel(controller.selectModel());
        table.removeColumn(table.getColumnModel().getColumn(0));
    }

    private Record getSelectedValue(int n) {
        Record r = null;
        if (n >= 0) {
            TableModel model = table.getModel();
            int id = (Integer) model.getValueAt(n, 0);
            String name = (String) model.getValueAt(n, 1);
            String composer = (String) model.getValueAt(n, 2);
            int year = (Integer) model.getValueAt(n, 3);
            boolean listened = (boolean) model.getValueAt(n, 4);
            r = new Record(id, name, composer, year, listened);
        }
        return r;
    }

    private void insert() {
        Record r = new Record();
        ViewRecordDialog modalDialog = new ViewRecordDialog(this, r, "Insert");
        modalDialog.pack();
        modalDialog.setVisible(true);
        r = modalDialog.getRecord();
        if (r != null && r.getName() != null) {
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

    private void search() {
        String name = txtSearch.getText();
        if (!name.equals("")) {
            DefaultTableModel model = controller.selectModelByName(name);
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No records found", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                table.setModel(model);
            }
        } else {
            reload();
        }
        txtSearch.setText("");
    }

    private void print() {
        ArrayList<Record> records = new ArrayList<>();
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            Record r = getSelectedValue(i);
            records.add(r);
        }
        controller.createPDF(records);
    }

    private void youtube() {
        int x = table.getSelectedRow();
        int y = table.getSelectedColumn();
        if (x != -1) {
            String name = table.getModel().getValueAt(x, y+1).toString();
            String url = "https://www.youtube.com/results?search_query=";
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if (c != ' ') {
                    url += c;
                } else {
                    url += "+";
                }
            }
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No record selected", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exit() {
        JOptionPane.showMessageDialog(null, "Good bye!", "Bye", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
