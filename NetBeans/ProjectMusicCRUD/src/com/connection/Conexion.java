/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author axegas
 */
public class Conexion {

    private static final String DB = "music_db";
    private static final String HOST = "localhost";
    private static final String USUARIO = "root";
    private static final String CLAVE = "apg946130";
    private static final String URL = "jdbc:mysql://" + HOST + ":3306/" + DB + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    /*
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
     */
 /*
    CREATE SCHEMA `music_db` ;
CREATE TABLE `music_db`.`record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `composer` VARCHAR(45) NOT NULL,
  `year` INT NULL,
  PRIMARY KEY (`id`));
    
    
    INSERT INTO record (name, composer, year) VALUES ("asd","asd",1985);
INSERT INTO record (name, composer, year) VALUES ("xfgj","zdsf",1986);
INSERT INTO record (name, composer, year) VALUES ("zdh","ngbhf",1999);
INSERT INTO record (name, composer, year) VALUES ("zdfh","sdf",2002);
INSERT INTO record (name, composer, year) VALUES ("zdfh","dfh",1981);
INSERT INTO record (name, composer, year) VALUES ("zdfh","sdfg",1955);
INSERT INTO record (name, composer, year) VALUES ("zfdh","dfh",2010);
    
    
    
    
     */
}
