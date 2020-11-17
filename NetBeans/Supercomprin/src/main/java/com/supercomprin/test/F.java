/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercomprin.test;

import com.conectar.Conexion;
import com.supercomprin.dao.CompraDAO;
import com.supercomprin.dao.DevolucionDAO;
import com.supercomprin.dao.ProductoDAO;
import com.supercomprin.dao.WalletDAO;
import com.supercomprin.model.Compra;
import com.supercomprin.model.Producto;
import com.supercomprin.model.Wallet;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author axegas
 */
public class F {//En esta clase implemento las funciones básicas para gestionar el supermercado

    public static void pagarCompraConSaldo(Wallet w, Producto p) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            CompraDAO daoc = new CompraDAO(conexion);
            WalletDAO daow = new WalletDAO(conexion);

            w.setPuntos(w.getPuntos() + p.getPuntos());
            w.setSaldo(w.getSaldo() - p.getPrecio());
            daow.update(w);

            Date now = new Date();//fecha de la compra: el momento actual
            Compra c = new Compra(p, w, now.toString());
            daoc.insert(c);

            conexion.commit();
            System.out.println("Gracias por su compra.");

        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            System.out.println("Rollback: ha habido algún error al hacer la compra.");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }

    public static void pagarCompraConPuntos(Wallet w, Producto p) {

        Connection conexion = null;
        try {

            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            CompraDAO daoc = new CompraDAO(conexion);
            WalletDAO daow = new WalletDAO(conexion);

            w.setPuntos(w.getPuntos() - (int) p.getPrecio());
            daow.update(w);

            Date now = new Date();//fecha de la compra: el momento actual
            Compra c = new Compra(p, w, now.toString());
            daoc.insert(c);

            conexion.commit();

            if (p.getPrecio() < 5) {
                System.out.println("Rollback: No puede comprar el producto.");
                conexion.rollback();
            } else {
                System.out.println("Gracias por su compra.");
            }

        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            System.out.println("Rollback: ha habido algún error al hacer la compra.");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }

    public static void devolverProducto(Compra c) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            CompraDAO daoc = new CompraDAO(conexion);
            WalletDAO daow = new WalletDAO(conexion);
            ProductoDAO daop = new ProductoDAO(conexion);

            Wallet w = daow.selectWallet(c.getWallet().getIdWallet());
            Producto p = daop.selectProducto(c.getProducto().getIdproducto());

            w.setPuntos(w.getPuntos() - p.getPuntos());
            w.setSaldo(w.getSaldo() + p.getPrecio());
            daow.update(w);

            daoc.delete(c);
            conexion.commit();

            if (w.getPuntos() - p.getPuntos() < 5) {
                System.out.println("Rollback: ha habido algún error al hacer la devolución.");
                conexion.rollback();
            } else {
                System.out.println("Devolución realizada con éxito.");
            }
        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            System.out.println("Rollback: ha habido algún error al hacer la devolución.");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

    }

    public static void devolverProductoDevolucion(Compra c) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            CompraDAO daoc = new CompraDAO(conexion);
            WalletDAO daow = new WalletDAO(conexion);
            ProductoDAO daop = new ProductoDAO(conexion);
            DevolucionDAO daod = new DevolucionDAO(conexion);

            Wallet w = daow.selectWallet(c.getWallet().getIdWallet());
            Producto p = daop.selectProducto(c.getProducto().getIdproducto());

            w.setPuntos(w.getPuntos() - p.getPuntos());
            w.setSaldo(w.getSaldo() + p.getPrecio());
            daow.update(w);

            //daoc.delete(c);
            
            
            conexion.commit();

            if (w.getPuntos() - p.getPuntos() < 5) {
                System.out.println("Rollback: ha habido algún error al hacer la devolución.");
                conexion.rollback();
            } else {
                System.out.println("Devolución realizada con éxito.");
            }
        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            System.out.println("Rollback: ha habido algún error al hacer la devolución.");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

    }

    public static void recargarWallet(Wallet w, int recarga) {
        Connection conexion = null;
        try {

            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            WalletDAO daow = new WalletDAO(conexion);
            w.setSaldo(w.getSaldo() + recarga);
            daow.update(w);

            conexion.commit();

            int dia = Integer.parseInt((new SimpleDateFormat("dd")).format(new Date()));
            if (dia > 5) {
                System.out.println("Rollback: No puede hacer la recarga.");
            } else {
                System.out.println("Recarga realizada con éxito.");
            }

        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            System.out.println("Rollback: ha habido algún error al hacer la Recarga.");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
