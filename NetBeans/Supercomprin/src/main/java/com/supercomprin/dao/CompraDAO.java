/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercomprin.dao;

import com.conectar.Conexion;
import com.supercomprin.model.Compra;
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
public class CompraDAO {

    private final static String SQL_SELECT = "select * from compra";
    private final static String SQL_INSERT = "insert into compra(idproducto, idwallet, fecha)values(?,?,?)";
    private final static String SQL_UPDATE = "update compra set idproducto=?,idwallet=?,fecha=? where idcompra=?";
    private final static String SQL_DELETE = "delete from compra where idcompra=?";

    private Connection conexionTransaccional;

    public CompraDAO() {
    }

    public CompraDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public ArrayList<Compra> select() throws SQLException {
        ArrayList<Compra> compras = new ArrayList<>();
        Compra c;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                c = crearCompra(rs);
                compras.add(c);
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
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        return compras;
    }

    //esta función recibe el id de una compra y devuelve la compra correspondiente, o null si no existe
    public Compra selectCompra(int idcompra) throws SQLException {
        Compra c = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT + " where idcompra = " + idcompra);
            rs = stmt.executeQuery();
            if (rs.next()) {
                c = crearCompra(rs);
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
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        return c;
    }

    public int insert(Compra c) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, c.getProducto().getIdproducto());
            stmt.setInt(2, c.getWallet().getIdWallet());
            stmt.setDate(3, c.getFecha());
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

    public int update(Compra c) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, c.getProducto().getIdproducto());
            stmt.setInt(2, c.getWallet().getIdWallet());
            stmt.setDate(3, c.getFecha());
            stmt.setInt(4, c.getIdCompra());
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

    public int delete(Compra c) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, c.getIdCompra());
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
    private Compra crearCompra(ResultSet rs) throws SQLException {
        int idproducto = rs.getInt("idproducto");
        int idcompra = rs.getInt("idcompra");
        int idwallet = rs.getInt("idwallet");
        Date fecha = rs.getDate("fecha");

        //a partir del idproducto y idwallet leidos de la tabla compra, busco los correspondientes datos necesarios para construir el objeto
        ProductoDAO daop = new ProductoDAO();
        WalletDAO daow = new WalletDAO();
        Producto producto = daop.selectProducto(idproducto);
        Wallet wallet = daow.selectWallet(idwallet);
        Compra compra = new Compra(idcompra, producto, wallet, fecha);
        return compra;
    }

}
