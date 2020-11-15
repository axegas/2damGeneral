/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercomprin.dao;

import com.conectar.Conexion;
import com.supercomprin.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author axegas
 */
public class ProductoDAO {

    private final static String SQL_SELECT = "select * from producto";
    private final static String SQL_INSERT = "insert into producto(nombre, puntos, precio)values(?,?,?)";
    private final static String SQL_UPDATE = "update producto set nombre=?, puntos=?, precio=? where idproducto=?";
    private final static String SQL_DELETE = "delete from producto where idproducto=?";

    private Connection conexionTransaccional;

    public ProductoDAO() {
    }

    public ProductoDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public ArrayList<Producto> select() throws SQLException {
        ArrayList<Producto> productos = new ArrayList<>();
        Producto p;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                p = crearProducto(rs);
                productos.add(p);
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

        return productos;
    }

    //esta función recibe el id de un producto y devuelve el producto correspondiente, o null si no existe
    public Producto selectProducto(int idproducto) throws SQLException {
        Producto p = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT + " where idproducto=" + idproducto);
            rs = stmt.executeQuery();
            while (rs.next()) {
                p = crearProducto(rs);
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
        return p;
    }

    public int insert(Producto p) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, p.getNombre());
            stmt.setInt(2, p.getPuntos());
            stmt.setInt(3, p.getPrecio());
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

    public int update(Producto p) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, p.getNombre());
            stmt.setInt(2, p.getPuntos());
            stmt.setInt(3, p.getPrecio());
            stmt.setInt(4, p.getIdproducto());
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

    public int delete(Producto p) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, p.getIdproducto());
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
    private Producto crearProducto(ResultSet rs) throws SQLException {
        int idproducto = rs.getInt("idproducto");
        String nombre = rs.getString("nombre");
        int puntos = rs.getInt("puntos");
        int precio = rs.getInt("precio");
        Producto w = new Producto(idproducto, nombre, puntos, precio);
        return w;
    }

}
