/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import clases.User;
import clases.PaqueteEnvio;
import clases.Util;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JTextArea;
import vista.VistaServidor;

/**
 *
 * @author peixe
 */
public class Servidor implements Runnable {

    private static ArrayList<User> usuarios;
    private final JTextArea pantalla;

    public Servidor(VistaServidor vista) {        
        this.pantalla = vista.pantalla;
        vista.setVisible(true);
        usuarios = new ArrayList<>();       
        pantalla.append("Servidor conectado\n");
        iniciar();
    }

    private void iniciar() {
        Thread mihilo = new Thread(this);
        mihilo.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(Util.PUERTO_SERVIDOR);
            while (true) {
                try (Socket misocket = servidor.accept(); ObjectInputStream paqueteDatos = new ObjectInputStream(misocket.getInputStream())) {
                    PaqueteEnvio paqueteRecibido = (PaqueteEnvio) paqueteDatos.readObject();
                    User destino = paqueteRecibido.getDestino();
                    User origen = paqueteRecibido.getOrigen();
                    if (destino == null) {
                        usuarios.add(origen);
                        PaqueteEnvio usuariosConectados = new PaqueteEnvio(null, origen, "");
                        usuariosConectados.setIps(usuarios);
                        pantalla.append("Conectado: " + origen + "\n");
                        usuarios.forEach(u -> {
                            try {
                                usuariosConectados.send(u.getIp(), Util.PUERTO_CLIENTE);
                            } catch (IOException e) {
                                e.printStackTrace(System.out);
                            }
                        });
                    } else {
                        paqueteRecibido.send(destino.getIp(), Util.PUERTO_CLIENTE);
                        pantalla.append(paqueteRecibido.toString() + "\n");
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }

    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor(new VistaServidor());
    }

}
