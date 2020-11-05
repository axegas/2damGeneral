/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Show;

/**
 *
 * @author axegas
 */
public class FrameShows extends JFrame{

    private JPanel pnlFrame;
    private JPanel pnlButtons;
    private JPanel pnlMain;
    private JButton btnFirst, btnPrevious, btnNext, btnLast, btnInsert, btnDelete, btnUpdate;
    private JLabel lblTitle, lblScreenwriter, lblSeasons, lblGenre, lblSeen, lblPlatform;
    private JTextField txtTitle, txtScreenwriter, txtSeasons, txtGenre, txtSeen, txtPlatform;
    private JComboBox cmbPlatform;
    private String[] platforms;
    private final Controller control;

    public FrameShows(Controller control) {
        this.control = control;
        initComponents();
        ponValoresEnTextFields(obtenShow());
        textFieldsHabilitados(false);
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 200, 600, 400);
        setTitle("My Shows");

        pnlFrame = new JPanel(new BorderLayout());
        pnlButtons = new JPanel(new FlowLayout());
        pnlMain = new JPanel(new GridLayout(7, 2, 10, 10));

        add(pnlFrame);
        pnlFrame.add(pnlButtons, BorderLayout.NORTH);
        pnlFrame.add(pnlMain, BorderLayout.CENTER);

        btnFirst = new JButton("|<");
        btnPrevious = new JButton("<");
        btnNext = new JButton(">");
        btnLast = new JButton(">|");
        btnInsert = new JButton("+");
        btnDelete = new JButton("-");
        btnUpdate = new JButton("*");

        btnFirst.addActionListener(e -> ponValoresEnTextFields(control.first()));
        btnPrevious.addActionListener(e -> ponValoresEnTextFields(control.previous()));
        btnNext.addActionListener(e -> ponValoresEnTextFields(control.next()));
        btnLast.addActionListener(e -> ponValoresEnTextFields(control.last()));
        btnInsert.addActionListener(e -> ponValoresEnTextFields(insert()));
        btnDelete.addActionListener(e -> ponValoresEnTextFields(delete()));
        btnUpdate.addActionListener(e -> ponValoresEnTextFields(update()));

        pnlButtons.add(btnFirst);
        pnlButtons.add(btnPrevious);
        pnlButtons.add(btnNext);
        pnlButtons.add(btnLast);
        pnlButtons.add(btnInsert);
        pnlButtons.add(btnDelete);
        pnlButtons.add(btnUpdate);

        lblTitle = new JLabel("Title");
        lblScreenwriter = new JLabel("Screenwriter");
        lblSeasons = new JLabel("Seasons");
        lblGenre = new JLabel("Genre");
        lblSeen = new JLabel("Seen Seasons");
        lblPlatform = new JLabel("Platform");

        txtTitle = new JTextField(50);
        txtScreenwriter = new JTextField(50);
        txtSeasons = new JTextField(50);
        txtGenre = new JTextField(50);
        txtSeen = new JTextField(50);
        txtPlatform = new JTextField(50);

        platforms = new String[]{"NETFLIX","HBO","DISNEY"};

        cmbPlatform = new JComboBox(platforms);
        cmbPlatform.setVisible(false);

        pnlMain.add(lblTitle);
        pnlMain.add(txtTitle);
        pnlMain.add(lblScreenwriter);
        pnlMain.add(txtScreenwriter);
        pnlMain.add(lblSeasons);
        pnlMain.add(txtSeasons);
        pnlMain.add(lblGenre);
        pnlMain.add(txtGenre);
        pnlMain.add(lblSeen);
        pnlMain.add(txtSeen);
        pnlMain.add(lblPlatform);
        pnlMain.add(txtPlatform);
        pnlMain.add(cmbPlatform);
    }

    private void botonesHabilitados(boolean state) {
        btnFirst.setEnabled(state);
        btnPrevious.setEnabled(state);
        btnNext.setEnabled(state);
        btnLast.setEnabled(state);
        btnDelete.setEnabled(state);
        btnUpdate.setEnabled(state);
        btnInsert.setEnabled(state);
    }

    private void textFieldsHabilitados(boolean state) {
        txtTitle.setEditable(state);
        txtScreenwriter.setEditable(state);
        txtSeasons.setEditable(state);
        txtGenre.setEditable(state);
        txtSeen.setEditable(state);
        txtPlatform.setEditable(state);

        cmbPlatform.setVisible(state);
        lblPlatform.setVisible(!state);
        txtPlatform.setVisible(!state);
    }

    private void ponValoresEnTextFields(Show s) {
        txtTitle.setText(s.getName());
        txtScreenwriter.setText(s.getScreenwriter());
        txtSeasons.setText(String.valueOf(s.getSeasons()));
        txtGenre.setText(s.getGenre());
        txtSeen.setText(String.valueOf(s.getSeasons_seen()));
        txtPlatform.setText(s.getPlatform());
    }

    private Show obtenShow() {
        Show s = new Show();
        if (control.getSize() == 0) {
            botonesHabilitados(false);
            btnInsert.setEnabled(true);
        } else {
            s = control.getShow();
        }
        return s;
    }

    private Show creaSerie() {
        return new Show(txtTitle.getText(), txtScreenwriter.getText(), Integer.parseInt(txtSeasons.getText()), txtGenre.getText(), Integer.parseInt(txtSeen.getText()), txtPlatform.getText());
    }

    private Show insert() {
        Show s;
        if (btnInsert.getText().equals("+")) {
            btnInsert.setText("+++");
            botonesHabilitados(false);
            btnInsert.setEnabled(true);
            textFieldsHabilitados(true);
            s = new Show();
        } else {
            btnInsert.setText("+");
            botonesHabilitados(true);
            textFieldsHabilitados(false);
            txtPlatform.setText(cmbPlatform.getSelectedItem().toString());
            try {
                s = creaSerie();
                control.insert(s);
            } catch (SQLException | NumberFormatException ex) {
                s = obtenShow();
                System.out.println(ex.getMessage());
            }
        }
        return s;
    }

    private Show update() {
        Show s = control.getShow();
        int id = s.getId();
        if (btnUpdate.getText().equals("*")) {
            btnUpdate.setText("***");
            botonesHabilitados(false);
            btnUpdate.setEnabled(true);
            textFieldsHabilitados(true);
        } else {
            btnUpdate.setText("*");
            botonesHabilitados(true);
            textFieldsHabilitados(false);
            txtPlatform.setText(cmbPlatform.getSelectedItem().toString());
            try {
                s = creaSerie();
                s.setId(id);
                control.update(s);
            } catch (SQLException | NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return s;
    }

    private Show delete() {
        int opc = JOptionPane.showConfirmDialog(this.getParent(), "¿Desea borrar la serie seleccionada?");
        if (JOptionPane.OK_OPTION == opc) {
            try {
                control.delete();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return obtenShow();
        } else {
            return control.getShow();            
        }
    }
}
