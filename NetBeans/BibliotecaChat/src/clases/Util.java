/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author peixe
 */
public class Util {

    public static final int PUERTO_SERVIDOR = 9999;
    public static final int PUERTO_CLIENTE = 9090;
    public static final String IP_SERVIDOR = "192.168.26.108";
    //192.168.26.108 -> clase
    public static String getIp() {
        String ip = "";
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("google.com", 80));
            ip = socket.getLocalAddress().getHostAddress();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ip;
    }
}
