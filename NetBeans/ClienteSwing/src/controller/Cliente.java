/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import clases.PaqueteEnvio;
import clases.User;
import clases.Util;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import vista.VistaCliente;

/**
 *
 * @author peixe
 */
public class Cliente implements Runnable {

    public ArrayList<User> usuarios;

    public User usuario;
    public JTextField txtEnviar;
    public JTextArea pantalla;
    public JComboBox<User> cmbUsers;

    public Cliente(VistaCliente vista) {  
        vista.setVisible(true);
        usuario = vista.usuario;
        txtEnviar = vista.txtEnviar;
        pantalla = vista.pantalla;
        cmbUsers = vista.cmbUsers;
        
        txtEnviar.addActionListener(e -> enviar());
        conectar();
        iniciar();
    }

    private void iniciar() {
        Thread mihilo = new Thread(this);
        mihilo.start();
    }

    private void conectar() {
        User destino = null;
        PaqueteEnvio paquete = new PaqueteEnvio(usuario, destino, "");
        try {
            paquete.send(Util.IP_SERVIDOR, Util.PUERTO_SERVIDOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviar() {
        String mensaje = txtEnviar.getText();
        txtEnviar.setText("");
        User destino = (User) cmbUsers.getSelectedItem();
        PaqueteEnvio paquete = new PaqueteEnvio(usuario, destino, mensaje);
        pantalla.append("Yo: " + mensaje + "\n");
        try {
            paquete.send(Util.IP_SERVIDOR, Util.PUERTO_SERVIDOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket servidorCliente = new ServerSocket(Util.PUERTO_CLIENTE);
            while (true) {
                try (Socket socketRecibido = servidorCliente.accept(); ObjectInputStream datosEntrada = new ObjectInputStream(socketRecibido.getInputStream())) {
                    PaqueteEnvio paqueteRecibido = (PaqueteEnvio) datosEntrada.readObject();
                    User origen = paqueteRecibido.getOrigen();
                    String mensaje = paqueteRecibido.getMensaje();

                    if (origen == null) {
                        usuarios = paqueteRecibido.getIps();
                        cmbUsers.removeAllItems();
                        usuarios.forEach(u -> cmbUsers.addItem(u));
                    } else {
                        pantalla.append(origen + ": " + mensaje + "\n");
                    }
                }

            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente(new VistaCliente());
    }

}
