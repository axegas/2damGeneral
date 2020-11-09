/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joptionpaneproyect;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author axegas
 */
public class Frame extends JFrame {

    public Frame() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 200, 400, 400);
        setTitle("Ejercicio 3");
        setLayout(new FlowLayout());
        Object[] o = new Object[]{"Info", "Advertencia", "Error", "Confirmación", "Texto", "Combo", "Más"};
        JButton btn0 = new JButton((String) o[0]);
        JButton btn1 = new JButton((String) o[1]);
        JButton btn2 = new JButton((String) o[2]);
        JButton btn3 = new JButton((String) o[3]);
        JButton btn4 = new JButton((String) o[4]);
        JButton btn5 = new JButton((String) o[5]);
        JButton btn6 = new JButton((String) o[6]);

        add(btn0);
        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(btn6);

        btn0.addActionListener(b -> pulsado(0));
        btn1.addActionListener(b -> pulsado(1));
        btn2.addActionListener(b -> pulsado(2));
        btn3.addActionListener(b -> pulsado(3));
        btn4.addActionListener(b -> pulsado(4));
        btn5.addActionListener(b -> pulsado(5));
        btn6.addActionListener(b -> pulsado(6));
    }

    private void pulsado(int i) {
        switch (i) {
            case 0:
                JOptionPane.showMessageDialog(null, "Este es un mensaje Simple tipo información");
                JOptionPane.showMessageDialog(null, "Otro mensaje Simple pero con título", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Este es un mensaje de advertencia", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Este es un mensaje de error", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Este es un mensaje de confirmación o pregunta", "QUESTION_MESSAGE", JOptionPane.QUESTION_MESSAGE);
                break;
            case 4:
                String num = JOptionPane.showInputDialog(null, "escribe un número para multiplicarlo por 2");
                try {
                    int n = Integer.parseInt(num);
                    JOptionPane.showMessageDialog(null, "El resultado es: " + n * 2);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El valor ingresado debe ser un número", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 5:
                String color = (String) JOptionPane.showInputDialog(null, "Seleccione un color", "Color", JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Amarillo", "Rojo", "Verde"}, null);
                JOptionPane.showMessageDialog(null, "Color seleccionado " + color);
                System.out.println(color);
                break;
            case 6:
                int opc = JOptionPane.showOptionDialog(null, "Usas mucho el JOptionPane?", "Selector de opciones", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, "Info");
                switch (opc) {
                    case 0:
                        //falta esto
                        JOptionPane.showInputDialog(null, "Seleccione", "titulo", JOptionPane.OK_CANCEL_OPTION, null, new Object[] { new JCheckBox("A veces"),new JCheckBox("A menudo") ,new JCheckBox("Siempre")}, "option 1");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Pues es muy útil");
                        break;
                    case 2:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
