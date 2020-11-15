/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercomprin.test;

import com.conectar.Conexion;
import com.supercomprin.dao.CompraDAO;
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
            //me aseguro que puedo comprar con puntos (el producto debe valer como mínimo 5€)
            if (p.getPrecio() < 5) {
                System.out.println("No puede comprar el producto.");
                return;
            }

            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            CompraDAO daoc = new CompraDAO(conexion);
            WalletDAO daow = new WalletDAO(conexion);

            w.setPuntos(w.getPuntos() - p.getPrecio());
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

    public static boolean devolverProducto(Compra c) {
        Connection conexion = null;
        boolean res;
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

            //comprobamos que, al hacer la devolución, el cliente no se va a quedar con menos de 5 puntos
            if (w.getPuntos() - p.getPuntos() < 5) {
                System.out.println("No puede devolver el producto.");
                conexion.close();
                return false;
            }

            w.setPuntos(w.getPuntos() - p.getPuntos());
            w.setSaldo(w.getSaldo() + p.getPrecio());
            daow.update(w);

            daoc.delete(c);
            conexion.commit();
            System.out.println("Devolución realizada con éxito.");
            res = true;
        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            System.out.println("Rollback: ha habido algún error al hacer la devolución.");
            res = false;
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
        return res;
    }

    public static void recargarWallet(Wallet w, int recarga) {
        Connection conexion = null;
        try {
            //con este bloque de codigo, obtengo el día actual, y valido que sea correcto para poder hacer la recarga.
            
            int dia = Integer.parseInt((new SimpleDateFormat("dd")).format(new Date()));
            if (dia > 5) {
                System.out.println("No puede hacer la recarga.");
                return;
            }

            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            WalletDAO daow = new WalletDAO(conexion);
            w.setSaldo(w.getSaldo() + recarga);
            daow.update(w);

            conexion.commit();
            System.out.println("Recarga realizada con éxito.");

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
