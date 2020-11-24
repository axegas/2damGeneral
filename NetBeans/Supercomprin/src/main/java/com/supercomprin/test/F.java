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
        boolean realizada = true;
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            CompraDAO daoc = new CompraDAO(conexion);
            WalletDAO daow = new WalletDAO(conexion);

            //actualizar los datos del wallet
            w.setPuntos(w.getPuntos() + p.getPuntos());
            w.setSaldo(w.getSaldo() - p.getPrecio());
            daow.update(w);

            //crear la nueva compra
            Compra c = new Compra(p, w, getNow());
            daoc.insert(c);

            conexion.commit();

        } catch (SQLException e) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                    System.out.println("Rollback: " + e.getMessage());
                } catch (SQLException ex) {
                    System.out.println("Error al hacer el rollback");
                }
            } else {
                System.out.println("No se ha podido conectar");
            }
            realizada = false;
        }
        if (realizada) {
            System.out.println("Gracias por su compra.");
        }
    }

    public static void pagarCompraConPuntos(Wallet w, Producto p) {
        boolean realizada = true;
        Connection conexion = null;
        int contador = 0;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            CompraDAO daoc = new CompraDAO(conexion);
            WalletDAO daow = new WalletDAO(conexion);

            //actualizar los datos del wallet
            w.setPuntos(w.getPuntos() - (int) p.getPrecio());
            daow.update(w);

            //crear la nueva compra
            Compra c = new Compra(p, w, getNow());
            daoc.insert(c);

            //comprobamos que el producto no valga menos de 5€
            if (p.getPrecio() < 5) {
                System.out.println("Rollback: No puede comprar el producto, el precio es menor de 5€.");
                conexion.rollback();
            }
            conexion.commit();

        } catch (SQLException e) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                    System.out.println("Rollback: " + e.getMessage());
                } catch (SQLException ex) {
                    System.out.println("Error al hacer el rollback");
                }
            } else {
                System.out.println("No se ha podido conectar");
            }
            realizada = false;
        }
        
        if (realizada) {
            System.out.println("Gracias por su compra");
        }
    }

    public static void devolverProducto(Compra c) {
        boolean devuelto = true;
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

            //actualizar los datos del wallet
            w.setPuntos(w.getPuntos() - p.getPuntos());
            w.setSaldo(w.getSaldo() + p.getPrecio());
            daow.update(w);

            //crear la devolución
            Devolucion d = new Devolucion(c, getNow());
            daod.insert(d);

            //comprobar que el wallet no se vaya a quedar con menos de 5 puntos
            if (w.getPuntos() - p.getPuntos() < 5) {
                System.out.println("Rollback: Puntos insuficientes.");
                conexion.rollback();
            }

            conexion.commit();
        } catch (SQLException e) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                    System.out.println("Rollback: " + e.getMessage());
                } catch (SQLException ex) {
                    System.out.println("Error al hacer el rollback");
                }
            } else {
                System.out.println("No se ha podido conectar");
            }
            devuelto = false;
        }
        if (devuelto) {
            System.out.println("Gracias por su devolución.");
        }
    }

    //a partir del wallet, y la cantidad que queremos recargar, recargamos su saldo
    public static void recargarWallet(Wallet w, int recarga) {
        boolean realizada = true;
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            //actualizar datos del wallet
            WalletDAO daow = new WalletDAO(conexion);
            w.setSaldo(w.getSaldo() + recarga);
            daow.update(w);

            //comprobar que estamos en la fecha correcta
//            int dia = Integer.parseInt((new SimpleDateFormat("dd")).format(new java.util.Date()));
//            if (dia > 5) {
//                conexion.rollback();
//                System.out.println("Rollback: No puede hacer la recarga, fuera de fecha.");
//            }
            conexion.commit();
        } catch (SQLException e) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                    System.out.println("Rollback: " + e.getMessage());
                } catch (SQLException ex) {
                    System.out.println("Error al hacer el rollback");
                }
            } else {
                System.out.println("No se ha podido conectar");
            }
            realizada = false;
        }
        if (realizada) {
            System.out.println("Gracias por su recarga.");
        }
    }

    //estos dos métodos los he tenido que implementar dada la poca compatibilidad entre el Date de java.util y el Date de java.sql
    //el primero devuelve la hora actual (para realizar las compras/devoluciones) en formato sql.date
    public static Date getNow() {
        java.util.Date fechaJAVA = new java.util.Date();
        Date fechaSQL = new Date(fechaJAVA.getTime());
        return fechaSQL;
    }

    //el siguiente devuelve, a partir de un string en formato "dd-MM-yyyy", el objeto de tipo sql.date
    public static Date getFecha(String strfecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
