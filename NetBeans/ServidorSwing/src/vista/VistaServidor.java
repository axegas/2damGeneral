package vista;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VistaServidor extends JFrame {

    private JPanel panel;
    public JTextArea pantalla;

    public VistaServidor() {
        initComponents();
    }

    private void initComponents() {
        setBounds(300, 300, 420, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Chat");

        panel = new JPanel(new GridLayout(1, 1, 10, 10));
        add(panel);

        pantalla = new JTextArea();
        pantalla.setEditable(false);

        panel.add(new JScrollPane(pantalla));
    }
}
