/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author peixe
 */
public class TestManejoPersonas {

    public static void main(String[] args) {

        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            PersonaDAO personaDao = new PersonaDAO(conexion);
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            Persona cambioPersona = new Persona();
            cambioPersona.setId(2);
            cambioPersona.setNombre("Karla Ivonne");
            cambioPersona.setApellidos("Gomez");
            cambioPersona.setEmail("kgomez@mail.com");
            cambioPersona.setEdad(111);
            personaDao.update(cambioPersona);
            
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("federico");
            nuevaPersona.setApellidos("Ramirez");
            nuevaPersona.setEmail("kgomez@mail.com");
            nuevaPersona.setEdad(444);
            personaDao.insert(nuevaPersona);
 
            nuevaPersona = new Persona();
            nuevaPersona.setNombre("yyyyyyyyyyyyyyyyyyyy");
            nuevaPersona.setApellidos("Ramirez");
            nuevaPersona.setEmail("kgomez@mail.commmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
            nuevaPersona.setEdad(333);
            personaDao.insert(nuevaPersona);

            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

        
    }
}
