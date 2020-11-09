/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joptionpaneproyect;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author axegas
 */
public class Menu extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

    public Menu() {
        initComponents();

    }

    private void initComponents() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 200, 400, 400);
        setTitle("Menu");

        setLayout(new BorderLayout());
        JPanel pnlMenuBar = new JPanel();
        JPanel pnlMain = new JPanel();

        add(pnlMenuBar, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);

        // MENU ITEMS:
        // We instantiate the menu bar.
        menuBar = new JMenuBar();

        // The first option on the menu.
        menu = new JMenu("First Menu");
        menuBar.add(menu);
        
        

        // to the first option of the menu we put two items.
        menuItem = new JMenuItem("Option 1");
        //menuItem.addActionListener(new em(this));
        menuItem.addActionListener(n -> abrirVentana(this));
        menu.add(menuItem);

        menu.addSeparator(); //separator
        menuItem = new JMenuItem("Option 2");
        menu.add(menuItem);

        // Second option in the menu bar without any item.
        menu = new JMenu("Second Menu");
        menuBar.add(menu);

        pnlMenuBar.add(menuBar);

    }

    public void abrirVentana(JFrame f) {
        DialogoModal dialogoModal = new DialogoModal(f);
        dialogoModal.pack();
        // to automatically resize the window.

        dialogoModal.setVisible(true);
        // As it is a modal window it will not return to this line 
        // until the other one is closed
        System.out.println(dialogoModal.getText());
    }
}
