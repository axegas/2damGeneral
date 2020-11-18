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
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Axel","Perez","54658745y","1988-06-28","axel@sdfh",default,default,DATEDIFF( current_date(),"1988-06-28")/365);
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Luis","Garcia","78451236u","1995-11-27","luisga@sdg",default,default,DATEDIFF( current_date(),"1995-11-27")/365);
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Aisha","Flordelis","78451202p","1990-12-24","aisha@sdfs",default,default,DATEDIFF( current_date(),"1990-12-24")/365);
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Ana","Gonzalez","78451245l","2001-05-17","ana@hfh",default,default,DATEDIFF( current_date(),"2001-05-17")/365);
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Luis","Perez","54658745y","1988-06-28","axel@sdfh",default,default,DATEDIFF( current_date(),"1988-06-28")/365);
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Amparo","Garcia","78451236u","1995-11-27","luisga@sdg",default,default,DATEDIFF( current_date(),"1995-11-27")/365);
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Lucia","Flordelis","78451202p","1990-12-24","aisha@sdfs",default,default,DATEDIFF( current_date(),"1990-12-24")/365);
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Josefa","Gonzalez","78451245l","2001-05-17","ana@hfh",default,default,DATEDIFF( current_date(),"2001-05-17")/365);   
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Pepito","Perez","54658745y","1988-06-28","axel@sdfh",default,default,DATEDIFF( current_date(),"1988-06-28")/365);
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Kevin","Garcia","78451236u","1995-11-27","luisga@sdg",default,default,DATEDIFF( current_date(),"1995-11-27")/365);
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Hector","Flordelis","78451202p","1990-12-24","aisha@sdfs",default,default,DATEDIFF( current_date(),"1990-12-24")/365);
            INSERT INTO wallet (nombre,apellidos,dni,fechanacimiento,email,puntos,saldo,edad) VALUES ("Jose","Gonzalez","78451245l","2001-05-17","ana@hfh",default,default,DATEDIFF( current_date(),"2001-05-17")/365); 
    
    
    
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
