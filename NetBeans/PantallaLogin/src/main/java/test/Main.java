/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import UI.MainFrame;
import datos.UserDAO;
import domain.User;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author axegas
 */
public class Main {

    public static void main(String[] args) {
         UserDAO dao = new UserDAO();       
         Date fecha = new Date(2020,11,10);
         dao.insert(new User("paco","123",fecha));
         


        //dao.insert(new Persona("Paco","Perez",32));
        //dao.insert(new Persona("Carlos","Perez",54));
        //   ArrayList<Persona> personas = dao.selectAll();
        //personas.get(0).setNombre("Feder");
        //System.out.println(dao.update(personas.get(0)));
        //dao.delete(personas.get(0));
        //dao.delete(personas.get(3));
        //personas = dao.selectAll();
        //   personas.forEach(p -> System.out.println(p));
        //MainFrame f = new MainFrame(new MainController());
        //f.setVisible(true);
        
    }

}
