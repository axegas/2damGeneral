/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joptionpaneproyect;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author axegas
 */
public class Casa extends JFrame implements ActionListener {

    private JMenuBar mnBar;
    private JMenu menuCasa, menuHabitaciones, menuExtras;
    private JMenuItem menuItem;
    private JPanel pnlMenuBar, pnlMain;
    private JCheckBoxMenuItem jCheckMenu1;
    private JRadioButtonMenuItem rbCocina, rbBanyo;
    private ButtonGroup group;

    public Casa() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 200, 400, 400);
        setTitle("Menu");

        setLayout(new BorderLayout());
        pnlMenuBar = new JPanel(new FlowLayout());
        pnlMain = new JPanel();

        add(pnlMenuBar, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);
        mnBar = new JMenuBar();

        pnlMenuBar.add(mnBar);
        menuCasa = new JMenu("Casa");
        mnBar.add(menuCasa);
        menuExtras = new JMenu("Extras");
        mnBar.add(menuExtras);

        menuHabitaciones = new JMenu("Habitaciones");
        menuCasa.add(menuHabitaciones);
        menuItem = new JMenuItem("Habitacion 1");
        menuHabitaciones.add(menuItem);
        menuItem = new JMenuItem("Habitacion 2");
        menuItem.setEnabled(false);
        menuHabitaciones.add(menuItem);

        jCheckMenu1 = new JCheckBoxMenuItem("Salón");
        jCheckMenu1.setSelected(true);
        menuCasa.add(jCheckMenu1);
        menuCasa.addSeparator();
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("new");
        menuItem.addActionListener(this);
        group = new ButtonGroup();

        rbCocina = new JRadioButtonMenuItem("Cocina");
        rbBanyo = new JRadioButtonMenuItem("Baño");

        group.add(rbCocina);
        group.add(rbBanyo);
        menuCasa.add(rbCocina);
        menuCasa.add(rbBanyo);

        menuItem = new JMenuItem("Garaje");
        menuExtras.add(menuItem);
        menuItem = new JMenuItem("Trastero");
        menuExtras.add(menuItem);

        ImageIcon imagen = new ImageIcon(getClass().getResource("/images/casa.png"));

        menuCasa.setIcon(imagen);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
