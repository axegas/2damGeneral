/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.view;

import com.projectmusiccrud.model.Record;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author peixe
 */
public class ViewRecord extends JFrame {

    private JTable table;
    private JPanel pnlTop, pnlBottom, pnlMain;
    private JMenuBar mnBar;
    private JButton btnUpdate, btnDelete;

    public ViewRecord() {
        initComponents();
    }
/*
    public void viewRecord(Record record) {
        System.out.println("Record data: " + record);
    }

    public void viewRecords(ArrayList<Record> records) {
        records.forEach(r -> System.out.println("Record data: " + r));
    }
*/
    public void viewRecordsTable(DefaultTableModel model) {
        table.setModel(model);
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 200, 600, 600);
        setTitle("Record table");
        
        mnBar = new JMenuBar();
        table = new JTable();
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        
        pnlMain = new JPanel(new GridLayout(2,1,10,10));
        pnlTop = new JPanel(new BorderLayout());
        pnlBottom = new JPanel(new FlowLayout());
        
        configMenuBar();
        pnlTop.add(mnBar, BorderLayout.NORTH);
        pnlTop.add(new JScrollPane(table), BorderLayout.CENTER);
        pnlBottom.add(btnUpdate);
        pnlBottom.add(btnDelete);
        
        add(pnlMain);
        pnlMain.add(pnlTop);
        pnlMain.add(pnlBottom);
    }

    private void configMenuBar() {
        JMenu menuOptions = new JMenu("Options");
        mnBar.add(menuOptions);
        JMenuItem menuItem = new JMenuItem("New");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuOptions.add(menuItem);
    }
}
