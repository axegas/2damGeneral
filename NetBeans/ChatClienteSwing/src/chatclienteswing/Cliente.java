/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclienteswing;

import clases.Usuario;
import clases.Paquete;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
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
public class Cliente extends JFrame {

    private final String casa = "192.168.1.34";
    private final String clase = "192.168.26.108";
    private Usuario user;
    private Usuario destino;
    private Thread thread;
    private JPanel panel;
    private JTextArea chat;
    private JTextField txtMensaje;
    private JComboBox listaUsers;
    private ArrayList<Usuario> usuarios;
    private Usuario[] usuariosArray;

    public Cliente() {
        usuarios = new ArrayList<>();
        initComponents();
        conectar();
        iniciar();
    }

    public static void main(String[] args) {
        new Cliente().setVisible(true);
    }

    private void iniciar() {
        thread = new Thread(this::threading);
        thread.setDaemon(true);
        thread.start();
    }

    private synchronized void conectar() {
        try {
            Paquete p = new Paquete(user, destino, "");
            destino = user;      
            p.send(casa, 9000);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }        
    }

    private void initComponents() {
        setBounds(300, 300, 420, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Chat");

        panel = new JPanel(null);
        setContentPane(panel);

        String m = JOptionPane.showInputDialog("Introduce tu nombre");
        user = new Usuario(m);
        user.setIp(getIp());

        JLabel lblNombre = new JLabel("Nombre: " + user);
        lblNombre.setBounds(20, 15, 100, 30);
        panel.add(lblNombre);

        listaUsers = new JComboBox();
        listaUsers.setBounds(240, 20, 150, 30);
        listaUsers.addItemListener(e -> destino = (Usuario) listaUsers.getSelectedItem());
        panel.add(listaUsers);

        chat = new JTextArea("");
        chat.setEditable(false);
        JScrollPane sp = new JScrollPane(chat);
        sp.setBounds(20, 60, 370, 200);
        panel.add(sp);

        txtMensaje = new JTextField();
        txtMensaje.setBounds(20, 280, 370, 50);
        txtMensaje.addActionListener(e -> enviar());
        panel.add(txtMensaje);

    }

    private synchronized void enviar() {
        try {
            Paquete p = new Paquete(user, destino, txtMensaje.getText());
            chat.append("Yo: " + txtMensaje.getText() + "\n");
            p.send(casa, 9000);
            txtMensaje.setText("");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void threading() {
        ServerSocket servidorCliente;
        Socket socketRecibido;
        Paquete paqueteEntrada;

        try {
            servidorCliente = new ServerSocket(9999);
            while (true) {
                socketRecibido = servidorCliente.accept();
                ObjectInputStream datosEntrada = new ObjectInputStream(socketRecibido.getInputStream());
                paqueteEntrada = (Paquete) datosEntrada.readObject();
                if (paqueteEntrada.getOrigen() != null) {
                    chat.append(paqueteEntrada + "\n");
                } else {
                    usuarios = paqueteEntrada.getUsuarios();
                    usuariosArray = new Usuario[usuarios.size()];
                    usuariosArray = usuarios.toArray(usuariosArray);                    
                    listaUsers.setModel(new DefaultComboBoxModel(usuariosArray));
                }
                socketRecibido.close();
            }
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String getIp() {
        String ip = "";
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("google.com", 80));
            ip = socket.getLocalAddress().getHostAddress();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return ip;
    }
}
