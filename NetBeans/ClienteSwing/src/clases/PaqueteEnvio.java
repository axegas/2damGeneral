package clases;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class PaqueteEnvio implements Serializable {

    private String nick;
    private String ip;
    private String mensaje;
    private User origen;
    private User destino;

    private ArrayList<User> usuarios;

    public PaqueteEnvio(User origen, User destino, String mensaje) {
        this.origen = origen;
        this.destino = destino;
        this.mensaje = mensaje;
        this.usuarios = new ArrayList<>();
    }

    public User getOrigen() {
        return origen;
    }

    public void setOrigen(User origen) {
        this.origen = origen;
    }

    public User getDestino() {
        return destino;
    }

    public void setDestino(User destino) {
        this.destino = destino;
    }

    public ArrayList<User> getIps() {
        return usuarios;
    }

    public void setIps(ArrayList<User> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNick() {
        return nick;
    }

    public String getIp() {
        return ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return "Envio: Origen=" + origen + ", Destino=" + destino + ", mensaje=" + mensaje;
    }

    public void send(String ipDestino, int puerto) throws IOException {
        ObjectOutputStream datosSalida;
        try (Socket enviaDestinatario = new Socket(ipDestino, puerto)) {
            datosSalida = new ObjectOutputStream(enviaDestinatario.getOutputStream());
            datosSalida.writeObject(this);
        }
        datosSalida.close();
    }

}
