/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.view;

import com.projectmusiccrud.controller.ControllerRecord;
import com.projectmusiccrud.model.Record;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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

    private JComboBox cmbListened;
    private JPopupMenu popup;
    private JTable table;
    private JPanel pnlTop, pnlBottom, pnlMain;
    private JMenuBar mnBar;
    private JButton btnSearch;
    private JTextField txtName, txtComposer, txtYear;
    private JLabel lblName, lblComposer, lblYear, lblListened;
    private final ControllerRecord controller;

    public ViewRecordMain() {
        this.controller = new ControllerRecord(); //inicializar el controlador
        initComponents(); //construir la base del jframe
        configMenuBar(); //configurar la barra superior de menu
        configPopUpMenu();//configurar PopUP del JTable
        reload(); //cargar los datos de la JTable
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 300, 900, 450);
        setTitle("Record table");

        table = new JTable();
        mnBar = new JMenuBar();
        popup = new JPopupMenu();

        btnSearch = new JButton("SEARCH - > ");
        btnSearch.addActionListener(t -> search());

        lblName = new JLabel("Name");
        lblComposer = new JLabel("Composer");
        lblYear = new JLabel("Year");
        txtName = new JTextField(15);
        txtComposer = new JTextField(15);
        txtYear = new JTextField(15);

        cmbListened = new JComboBox();
        cmbListened.addItem("No listened");
        cmbListened.addItem("Listened");
        cmbListened.addItem("All");

        //eventos de ratón en la tabla
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {//doble click
                    update();
                } else {
                    if (evt.getButton() == 3) {//click derecho
                        popup.show(evt.getComponent(), evt.getX(), evt.getY());
                    }
                }
            }
        });

        pnlMain = new JPanel(new BorderLayout());
        pnlTop = new JPanel(new BorderLayout());
        pnlBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTop.add(mnBar, BorderLayout.NORTH);
        pnlTop.add(new JScrollPane(table), BorderLayout.CENTER);

        pnlBottom.add(btnSearch);
        pnlBottom.add(lblName);
        pnlBottom.add(txtName);
        pnlBottom.add(lblComposer);
        pnlBottom.add(txtComposer);
        pnlBottom.add(lblYear);
        pnlBottom.add(txtYear);
        pnlBottom.add(cmbListened);

        add(pnlMain);
        pnlMain.add(pnlTop, BorderLayout.CENTER);
        pnlMain.add(pnlBottom, BorderLayout.SOUTH);

    }

    //barra de menu
    private void configMenuBar() {
        JMenu menuOptions = new JMenu("Options");
        JMenu menuOther = new JMenu("Other");

        mnBar.add(menuOptions);
        mnBar.add(menuOther);

        JMenuItem menuItem = new JMenuItem("New");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuOptions.add(menuItem);
        menuItem.addActionListener(t -> insert());

        menuItem = new JMenuItem("Update");
        menuItem.setMnemonic(KeyEvent.VK_U);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        menuOptions.add(menuItem);
        menuItem.addActionListener(t -> update());

        menuItem = new JMenuItem("Delete");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        menuOptions.add(menuItem);
        menuItem.addActionListener(t -> delete());

        menuOptions.addSeparator();

        menuItem = new JMenuItem("Delete All");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        menuOptions.add(menuItem);
        menuItem.addActionListener(t -> deleteAll());

        menuItem = new JMenuItem("Print");
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        menuOther.add(menuItem);
        menuItem.addActionListener(t -> print());

        menuItem = new JMenuItem("Search in Youtube");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuOther.add(menuItem);
        menuItem.addActionListener(t -> youtube());

        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        menuOther.add(menuItem);
        menuItem.addActionListener(t -> exit());
    }

    //menu popup
    private void configPopUpMenu() {

        JMenuItem menuItem = new JMenuItem("New");
        menuItem.addActionListener(t -> insert());
        popup.add(menuItem);

        menuItem = new JMenuItem("Update");
        menuItem.addActionListener(t -> update());
        popup.add(menuItem);

        menuItem = new JMenuItem("Delete");
        menuItem.addActionListener(t -> delete());
        popup.add(menuItem);
    }

    //cargar datos de la tabla. la columna ID la oculto
    private void reload() {
        table.setModel(controller.selectModel());
        table.removeColumn(table.getColumnModel().getColumn(0));
    }

    //metodo para, a partir de la fila seleccionada, obtener el disco seleccionado
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

    //método para insertar el disco
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

    //método para actualizar el disco
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

    //método para borrar el disco
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

    //método para borrar todos los discos
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

    //método para buscar un disco en la BD. se puede buscar por nombre, por compositor o por año, o una combinación de las tres cosas, para hacer
    //una busqueda más específica
    private void search() {
        String name = txtName.getText();
        String composer = txtComposer.getText();
        String year = txtYear.getText();
        int n = 0;
        if (year.length() > 0) {
            try {
                n = Integer.parseInt(year);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Numeric error", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        DefaultTableModel model = controller.specificSearch(name, composer, n, cmbListened.getSelectedIndex());
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No records found", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            table.setModel(model);
            table.removeColumn(table.getColumnModel().getColumn(0));
        }
        txtName.setText("");
        txtComposer.setText("");
        txtYear.setText("");

    }

    //imprimir en PDF
    private void print() {
        ArrayList<Record> records = new ArrayList<>();
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            Record r = getSelectedValue(i);
            records.add(r);
        }
        controller.createPDF(records);
    }

    //buscar en youtube
    private void youtube() {
        int x = table.getSelectedRow();
        int y = table.getSelectedColumn();
        if (x != -1) {
            String name = table.getModel().getValueAt(x, y + 1).toString();
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

    //mensaje de despedida
    private void exit() {
        JOptionPane.showMessageDialog(null, "Good bye!", "Bye", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

}
