/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercomprin.dao;

import com.conectar.Conexion;
import com.supercomprin.model.Compra;
import com.supercomprin.model.Devolucion;
import com.supercomprin.model.Producto;
import com.supercomprin.model.Wallet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author axegas
 */
public class DevolucionDAO {

    private final static String SQL_SELECT = "select * from devolucion";
    private final static String SQL_INSERT = "insert into devolucion(idproducto, idwallet, fecha)values(?,?,?)";
    private final static String SQL_UPDATE = "update devolucion set idproducto=?,idwallet=?,fecha=? where iddevolucion=?";
    private final static String SQL_DELETE = "delete from devolucion where iddevolucion=?";

    private Connection conexionTransaccional;

    public DevolucionDAO() {
    }

    public DevolucionDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public ArrayList<Devolucion> select() throws SQLException {
        ArrayList<Devolucion> devoluciones = new ArrayList<>();
        Devolucion d;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                d = crearDevolucion(rs);
                devoluciones.add(d);
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

        return devoluciones;
    }

    //esta función recibe el id de una compra y devuelve la compra correspondiente, o null si no existe
    public Devolucion selectCompra(int iddevolucion) throws SQLException {
        Devolucion d = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT + " where iddevolucion = " + iddevolucion);
            rs = stmt.executeQuery();
            while (rs.next()) {
                d = crearDevolucion(rs);
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

        return d;
    }

    public int insert(Devolucion d) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, d.getProducto().getIdproducto());
            stmt.setInt(2, d.getWallet().getIdWallet());
            stmt.setDate(3, d.getFecha());
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

    public int update(Devolucion c) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, c.getProducto().getIdproducto());
            stmt.setInt(2, c.getWallet().getIdWallet());
            stmt.setDate(3, c.getFecha());
            stmt.setInt(4, c.getiddevolucion());
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

    public int delete(Devolucion d) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, d.getiddevolucion());
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
    private Devolucion crearDevolucion(ResultSet rs) throws SQLException {
        int idproducto = rs.getInt("idproducto");
        int idcompra = rs.getInt("iddevolucion");
        int idwallet = rs.getInt("idwallet");
        Date fecha = rs.getDate("fecha");
        
        //a partir del idproducto y idwallet leidos de la tabla devolución, busco los correspondientes datos necesarios para construir el objeto
        ProductoDAO daop = new ProductoDAO();
        WalletDAO daow = new WalletDAO();
        Producto producto = daop.selectProducto(idproducto);
        Wallet wallet = daow.selectWallet(idwallet);
        Devolucion devolucion = new Devolucion(idcompra, producto, wallet, fecha);
        return devolucion;
    }

}
