/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conectar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author axegas
 */
public class Conexion {

    private static final String DB = "supercomprin";
    private static final String HOST = "localhost";
    private static final String USUARIO = "root";
    private static final String CLAVE = "apg946130";
    private static final String URL = "jdbc:mysql://" + HOST + ":3306/" + DB + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(PreparedStatement stm) throws SQLException {
        stm.close();
    }

    public static void close(Connection con) throws SQLException {
        con.close();
    }

    /*            
            //DATOS DE PRUEBA: 
            
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Axel","Perez","54658745y","28-06-1988","axel@sdfh");
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Luis","Garcia","78451236u","27-11-1995","luisga@sdg");
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Aisha","Flordelis","78451202p","24-12-1990","aisha@sdfs");
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Ana","Gonzalez","78451245l","17-05-2001","ana@hfh");
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Luis","Perez","54658745y","28-06-1988","axel@sdfh");
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Amparo","Garcia","78451236u","27-11-1995","luisga@sdg");
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Lucia","Flordelis","78451202p","24-12-1990","aisha@sdfs");
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Josefa","Gonzalez","78451245l","17-05-2001","ana@hfh");   
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Pepito","Perez","54658745y","28-06-1988","axel@sdfh");
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Kevin","Garcia","78451236u","27-11-1995","luisga@sdg");
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Hector","Flordelis","78451202p","24-12-1990","aisha@sdfs");
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email) VALUES ("Jose","Gonzalez","78451245l","17-05-2001","ana@hfh");  
            
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Jarrón",1,5);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Televisor",10,100);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Chaqueta",5,50);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Gorra",1,3);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Sofa",50,500);         
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Chicles",1,5);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Impresora",10,100);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Microondas",5,50);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Kinder",1,3);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Estanteria",50,500);          
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Macarrones",1,10);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Vino",6,60);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Tomates",1,1);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Lechuga",1,10);
            INSERT INTO producto (nombre, puntos, precio) VALUES ("Patatas",1,2);            
     */
}
