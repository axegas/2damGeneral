/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import clases.User;
import clases.PaqueteEnvio;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import vista.VistaServidor;

/**
 *
 * @author peixe
 */
public class Servidor implements Runnable {

    private static ArrayList<User> usuarios;
    private final int PUERTO_SERVIDOR = 9999;
    private final int PUERTO_CLIENTE = 9090;
    private final VistaServidor vista;

    public Servidor(VistaServidor vista) {
        this.vista = vista;
        vista.setVisible(true);
        usuarios = new ArrayList<>();
        this.iniciar();
        this.vista.pantalla.append("Servidor conectado\n");
    }

    private void iniciar() {
        Thread mihilo = new Thread(this);
        mihilo.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(PUERTO_SERVIDOR);
            while (true) {
                try (Socket misocket = servidor.accept(); ObjectInputStream paqueteDatos = new ObjectInputStream(misocket.getInputStream())) {
                    PaqueteEnvio paqueteRecibido = (PaqueteEnvio) paqueteDatos.readObject();
                    User destino = paqueteRecibido.getDestino();
                    User origen = paqueteRecibido.getOrigen();
                    if (destino == null) {
                        usuarios.add(origen);
                        PaqueteEnvio usuariosConectados = new PaqueteEnvio(null, origen, "");
                        usuariosConectados.setIps(usuarios);
                        vista.pantalla.append("Conectado: " + origen + "\n");
                        usuarios.forEach(u -> {
                            try {
                                usuariosConectados.send(u.getIp(), PUERTO_CLIENTE);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    } else {
                        paqueteRecibido.send(destino.getIp(), PUERTO_CLIENTE);
                        vista.pantalla.append(paqueteRecibido.toString());
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        Servidor server = new Servidor(new VistaServidor());
    }

}
