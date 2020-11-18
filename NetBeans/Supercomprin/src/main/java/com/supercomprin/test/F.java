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
import com.supercomprin.model.Devolucion;
import com.supercomprin.model.Producto;
import com.supercomprin.model.Wallet;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.ParseException;

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

            Compra c = new Compra(p, w, getNow());
            daoc.insert(c);

            conexion.commit();
            System.out.println("Gracias por su compra.");

        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            try {
                if (conexion == null) {
                    System.out.println("no se ha podido conectar");
                } else {
                    conexion.rollback();
                    System.out.println("Rollback: ha habido algún error al hacer la compra.");
                }
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

            Compra c = new Compra(p, w, getNow());
            daoc.insert(c);

            //comprobamos que el producto no valga menos de 5€
            if (p.getPrecio() < 5) {
                System.out.println("Rollback: No puede comprar el producto.");
                conexion.rollback();
            }
            conexion.commit();

        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            try {
                if (conexion == null) {
                    System.out.println("no se ha podido conectar");
                } else {
                    conexion.rollback();
                    System.out.println("Rollback: ha habido algún error al hacer la compra.");
                }
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

            WalletDAO daow = new WalletDAO(conexion);
            ProductoDAO daop = new ProductoDAO(conexion);
            DevolucionDAO daod = new DevolucionDAO(conexion);

            Wallet w = daow.selectWallet(c.getWallet().getIdWallet());
            Producto p = daop.selectProducto(c.getProducto().getIdproducto());

            w.setPuntos(w.getPuntos() - p.getPuntos());
            w.setSaldo(w.getSaldo() + p.getPrecio());
            daow.update(w);

            Devolucion d = new Devolucion(c, getNow());
            daod.insert(d);

            //comprobamos que el wallet no se vaya a quedar con menos de 5 puntos
            if (w.getPuntos() - p.getPuntos() < 5) {
                System.out.println("Rollback: ha habido algún error al hacer la devolución.");
                conexion.rollback();
            }

            conexion.commit();
        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            try {
                if (conexion == null) {
                    System.out.println("no se ha podido conectar");
                } else {
                    conexion.rollback();
                    System.out.println("Rollback: ha habido algún error al hacer la devolución.");
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }

    //a partir del wallet, y la cantidad que queremos recargar, recargamos su saldo
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

            //comprobamos que estemos en la fecha correcta
            int dia = Integer.parseInt((new SimpleDateFormat("dd")).format(new java.util.Date()));
            if (dia > 5) {
                conexion.rollback();
                System.out.println("Rollback: No puede hacer la recarga.");
            }
            conexion.commit();
        } catch (SQLException e) {
            //e.printStackTrace(System.out);            
            try {
                if (conexion == null) {
                    System.out.println("no se ha podido conectar");
                } else {
                    conexion.rollback();
                    System.out.println("Rollback: ha habido algún error al hacer la Recarga.");
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }

    //estos dos métodos los he tenido que implementar dada la poca compatibilidad entre el Date de java.util y el Date de java.sql
    //el primero devuelve la hora actual (para realizar las compras/devoluciones) en formato sql.date
    public static Date getNow() {
        java.util.Date fechaJAVA = new java.util.Date();
        Date fechaSQL = new Date(fechaJAVA.getTime());
        return fechaSQL;
    }

    //el siguiente devuelve, a partir de un string en formato "yyyy-MM-dd", el objeto de tipo sql.date
    public static Date getFecha(String strfecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaSQL = null;
        try {
            java.util.Date fechaJAVA = sdf.parse(strfecha);
            fechaSQL = new Date(fechaJAVA.getTime());
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }
        return fechaSQL;
    }
}
