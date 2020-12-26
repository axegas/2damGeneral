/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.view.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import mx.com.gm.sga.controller.ControllerCompras;
import mx.com.gm.sga.controller.ControllerProductos;
import mx.com.gm.sga.controller.ControllerWallets;
import mx.com.gm.sga.util.SupercomprinTableModel;
import mx.com.gm.sga.util.Util;

/**
 *
 * @author peixe
 */
public class PrincipalSwing extends JFrame {

    private JComboBox cmbOptions;
    private JTable table;
    private JPanel pnlTop, pnlMain;
    private JMenuBar mnBar;
    private final ControllerWallets controlW = new ControllerWallets();
    private final ControllerProductos controlP = new ControllerProductos();
    private final ControllerCompras controlC = new ControllerCompras();

    public static void main(String args[]) {
        PrincipalSwing p = new PrincipalSwing();
        p.setVisible(true);
    }

    public PrincipalSwing() {
        initComponents(); //construir la base del jframe
        configMenuBar(); //configurar la barra superior de menu
        setModel(1);
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 300, 1000, 450);
        setTitle("Super Comprin");

        table = new JTable();
        mnBar = new JMenuBar();

        cmbOptions = new JComboBox();
        cmbOptions.addItem("Wallets");
        cmbOptions.addItem("Productos");
        cmbOptions.addItem("Compras");

        pnlMain = new JPanel(new BorderLayout());
        pnlTop = new JPanel(new BorderLayout());

        pnlTop.add(mnBar, BorderLayout.NORTH);
        pnlTop.add(new JScrollPane(table), BorderLayout.CENTER);

        add(pnlMain);
        pnlMain.add(pnlTop, BorderLayout.CENTER);
    }

    //barra de menu
    private void configMenuBar() {
        JMenu menuWallets = new JMenu("Wallets");
        JMenu menuProductos = new JMenu("Productos");
        JMenu menuCompras = new JMenu("compras");

        mnBar.add(menuWallets);
        mnBar.add(menuProductos);
        mnBar.add(menuCompras);

        //--------------wallets-----------//
        JMenuItem menuItem = new JMenuItem("Mostrar todas");
        menuItem.setMnemonic(KeyEvent.VK_M);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(i -> setModel(1));
        menuWallets.add(menuItem);

        menuItem = new JMenuItem("Nueva");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(i -> nuevaWallet());
        menuWallets.add(menuItem);

        menuItem = new JMenuItem("Borrar");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(i -> borrarWallet());
        menuWallets.add(menuItem);

        menuItem = new JMenuItem("Recargar Saldo");
        menuItem.setMnemonic(KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(i -> recargarSaldo());
        menuWallets.add(menuItem);

        menuWallets.addSeparator();

        menuItem = new JMenuItem("Recargar saldo a todas");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(i -> recargarSaldoWallets());
        menuWallets.add(menuItem);

        //--------------productos-----------//
        menuItem = new JMenuItem("Mostrar todos");
        menuItem.addActionListener(i -> setModel(2));
        menuProductos.add(menuItem);

        menuItem = new JMenuItem("Nuevo");
        menuItem.addActionListener(i -> nuevoProducto());
        menuProductos.add(menuItem);

        menuItem = new JMenuItem("Borrar");
        menuItem.addActionListener(i -> borrarProducto());
        menuProductos.add(menuItem);

    }

    private void setModel(int opc) {
        SupercomprinTableModel model = new SupercomprinTableModel();
        switch (opc) {
            case 1:
                model.setModelWallets(controlW.mostrarWallets());
                break;
            case 2:
                model.setModelProductos(controlP.mostrarProductos());
                break;
            case 3:
                model.setModelCompras(controlC.mostrarCompras());
                break;
        }
        table.setModel(model);
    }

    private void borrarWallet() {
        int n = table.getSelectedRow();
        if (n == -1) {
            JOptionPane.showMessageDialog(null, "No has seleccionado ningún valor", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int opc = JOptionPane.showOptionDialog(null, "Estas seguro?", "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, "Info");
            if (opc == 0) {
                int id = (int) table.getModel().getValueAt(n, 0);
                controlW.borrarWallet(id);
                setModel(1);
            }
        }
    }

    private void nuevaWallet() {
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JTextField field3 = new JTextField();
        JTextField field4 = new JTextField();
        JTextField field5 = new JTextField();
        Object[] message = {
            "NOMBRE:", field1,
            "APELLIDOS:", field2,
            "DNI:", field3,
            "FECHA NACIMIENTO:", field4,
            "E-MAIL:", field5};
        int option = JOptionPane.showConfirmDialog(this, message, "Introduce los datos", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nombre = field1.getText();
            String apellidos = field2.getText();
            String dni = field3.getText();
            String fechaNacimiento = field4.getText();
            String email = field5.getText();

            Date fecha = null;
            long tedad = 0;
            int edad = 0;
            try {
                fecha = Util.stringToDate(fechaNacimiento);
                tedad = Util.hoy().getTime() - fecha.getTime();
                edad = (int) (tedad / 31556900000L);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

            controlW.nuevaWallet(nombre, apellidos, dni, fecha, email, edad);
            setModel(1);
        }
    }

    private void recargarSaldo() {
        int n = table.getSelectedRow();
        if (n == -1) {
            JOptionPane.showMessageDialog(null, "No has seleccionado ningún valor", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int id = (int) table.getModel().getValueAt(n, 0);
            String s = JOptionPane.showInputDialog("Introduce la cantidad");
            float f = Float.parseFloat(s);
            controlW.recargarSaldoWallet(id, f);
            setModel(1);
        }
    }

    private void recargarSaldoWallets() {
        controlW.recargasSaldoAleatorias();
        setModel(1);
    }

    private void nuevoProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void borrarProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
