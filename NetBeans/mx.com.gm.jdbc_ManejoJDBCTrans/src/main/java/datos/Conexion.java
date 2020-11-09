/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author axegas
 */
public class Conexion {

    private static final String DB = "test";
    private static final String HOST = "localhost";
    private static final String USUARIO = "root";
    private static final String CLAVE = "apg946130";
    private static final String URL = "jdbc:mysql://" + HOST + ":3306/" + DB + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

}
