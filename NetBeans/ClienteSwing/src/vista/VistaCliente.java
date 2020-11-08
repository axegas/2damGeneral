/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import clases.User;
import clases.Util;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author peixe
 */
public class VistaCliente extends JFrame {

    private String nombre;
    private JPanel pnlMain, pnlCabecera;
    private JLabel lblNombre;

    public User usuario;
    public JTextField txtEnviar;
    public JTextArea pantalla;
    public JComboBox<User> cmbUsers;

    public VistaCliente() {
        initComponents();
    }

    private void initComponents() {
        setBounds(300, 300, 420, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Chat");

        nombre = JOptionPane.showInputDialog(this, "Introduce tu nick:");
        usuario = new User(nombre, Util.getIp());

        lblNombre = new JLabel(nombre);
        cmbUsers = new JComboBox<>();

        pantalla = new JTextArea();
        pantalla.setEditable(false);
        txtEnviar = new JTextField(10);

        pnlMain = new JPanel(new BorderLayout());
        pnlCabecera = new JPanel(new FlowLayout());

        add(pnlMain);
        pnlMain.add(pnlCabecera, BorderLayout.NORTH);
        pnlMain.add(new JScrollPane(pantalla), BorderLayout.CENTER);
        pnlMain.add(txtEnviar, BorderLayout.SOUTH);

        pnlCabecera.add(lblNombre);
        pnlCabecera.add(cmbUsers);

    }
}
