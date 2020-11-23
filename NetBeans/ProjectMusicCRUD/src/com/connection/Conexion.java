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
    CREATE SCHEMA `music_db` ;
    DROP TABLE record;
    CREATE TABLE IF NOT EXISTS `music_db`.`record` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `name` VARCHAR(45) NOT NULL,
        `composer` VARCHAR(45) NOT NULL,
        `year` INT NULL,
        `listened` INT NULL DEFAULT 0,
        PRIMARY KEY (`id`));
    
    
    INSERT INTO record (name, composer, year) VALUES ("Disk 1","Composer 1",1985);
    INSERT INTO record (name, composer, year) VALUES ("Disk 2","Composer 2",1986);
    INSERT INTO record (name, composer, year) VALUES ("Disk 3","Composer 3",1999);
    INSERT INTO record (name, composer, year) VALUES ("Disk 4","Composer 4",2002);
    INSERT INTO record (name, composer, year) VALUES ("Disk 5","Composer 5",1981);
    INSERT INTO record (name, composer, year) VALUES ("Disk 6","Composer 6",1955);
    INSERT INTO record (name, composer, year) VALUES ("Disk 7","Composer 7",2010);
    INSERT INTO record (name, composer, year) VALUES ("Disk 8","Composer 8",1985);
    INSERT INTO record (name, composer, year) VALUES ("Disk 9","Composer 9",1986);
    INSERT INTO record (name, composer, year) VALUES ("Disk 10","Composer 10",1999);
    INSERT INTO record (name, composer, year) VALUES ("Disk 11","Composer 11",2002);
    INSERT INTO record (name, composer, year) VALUES ("Disk 12","Composer 12",1981);
    INSERT INTO record (name, composer, year) VALUES ("Disk 13","Composer 13",1955);
    INSERT INTO record (name, composer, year) VALUES ("Disk 14","Composer 14",2010);
    INSERT INTO record (name, composer, year) VALUES ("Disk 15","Composer 15",1985);
    INSERT INTO record (name, composer, year) VALUES ("Disk 16","Composer 16",1986);
    INSERT INTO record (name, composer, year) VALUES ("Disk 17","Composer 17",1999);
    INSERT INTO record (name, composer, year) VALUES ("Disk 18","Composer 18",2002);
    INSERT INTO record (name, composer, year) VALUES ("Disk 19","Composer 19",1981);
    INSERT INTO record (name, composer, year) VALUES ("Disk 20","Composer 20",1955);
    INSERT INTO record (name, composer, year) VALUES ("Disk 21","Composer 21",2010);
     */
}
