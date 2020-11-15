/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercomprin.dao;

import com.conectar.Conexion;
import com.supercomprin.model.Wallet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author axegas
 */
public class WalletDAO {

    private final static String SQL_SELECT = "select * from wallet";
    private final static String SQL_INSERT = "insert into wallet(nombre, apellidos, dni, fechanacimiento, email)values(?,?,?,?,?)";
    private final static String SQL_UPDATE = "update wallet set nombre=?,apellidos=?,dni=?,fechanacimiento=?,email=?, puntos=?, saldo=? where idwallet=?";
    private final static String SQL_DELETE = "delete from wallet where idwallet=?";

    private Connection conexionTransaccional;

    public WalletDAO() {
    }

    public WalletDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public ArrayList<Wallet> select() throws SQLException {
        ArrayList<Wallet> wallets = new ArrayList<>();
        Wallet w;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                w = crearWallet(rs);
                wallets.add(w);
            }
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return wallets;
    }

    //esta función recibe el id de una wallet y devuelve la wallet correspondiente, o null si no existe
    public Wallet selectWallet(int idwallet) throws SQLException {
        Wallet w = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT + " where idwallet=" + idwallet);
            rs = stmt.executeQuery();
            while (rs.next()) {
                w = crearWallet(rs);
            }
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return w;
    }

    public int insert(Wallet w) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, w.getNombre());
            stmt.setString(2, w.getApellidos());
            stmt.setString(3, w.getDni());
            stmt.setString(4, w.getFechaNacimiento());
            stmt.setString(5, w.getEmail());
            registros = stmt.executeUpdate();
        } finally {
            try {
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int update(Wallet w) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, w.getNombre());
            stmt.setString(2, w.getApellidos());
            stmt.setString(3, w.getDni());
            stmt.setString(4, w.getFechaNacimiento());
            stmt.setString(5, w.getEmail());
            stmt.setInt(6, w.getPuntos());
            stmt.setInt(7, w.getSaldo());
            stmt.setInt(8, w.getIdWallet());
            registros = stmt.executeUpdate();
        } finally {
            try {
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int delete(Wallet w) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, w.getIdWallet());
            registros = stmt.executeUpdate();
        } finally {
            try {
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    //este método me sirve para leer, a partir del ResultSet, el objeto actual, para que esté un poco más claro
    private Wallet crearWallet(ResultSet rs) throws SQLException {
        int idwallet = rs.getInt("idwallet");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        String dni = rs.getString("dni");
        String fechanacimiento = rs.getString("fechanacimiento");
        String email = rs.getString("email");
        int puntos = rs.getInt("puntos");
        int saldo = rs.getInt("saldo");
        Wallet wallet = new Wallet(idwallet, nombre, apellidos, dni, fechanacimiento, email, puntos, saldo);
        return wallet;
    }

}
