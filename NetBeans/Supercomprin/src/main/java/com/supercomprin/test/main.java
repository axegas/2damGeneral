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
import java.util.Date;

/**
 *
 * @author axegas
 */
public class main {

    public static void main(String args[]) {

        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            CompraDAO daoc = new CompraDAO(conexion);
            WalletDAO daow = new WalletDAO(conexion);
            ProductoDAO daop = new ProductoDAO(conexion);

            Producto p = daop.selectProducto(34);
            Wallet w = daow.selectWallet(34);

            System.out.println(p);
            System.out.println(w);

//            daow.insert(w);
//            daop.insert(p);
//            System.out.println("insertados");
            //w.setIdWallet(34);
            //w.setSaldo(20);
            //daow.update(w);
            w.setPuntos(w.getPuntos() + p.getPuntos());
            w.setSaldo(w.getSaldo() - p.getPrecio());
            daow.update(w);
            conexion.rollback();
            //System.out.println("producto comprado");

            Date now = new Date();//fecha de la compra: el momento actual
            Compra c = new Compra(p, w, now.toString());
            daoc.insert(c);
            System.out.println("compra insertada");

            conexion.commit();
            System.out.println("Gracias por su compra.");

        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            System.out.println("Rollback: ha habido algún error al hacer la compra.");
            try {
                conexion.rollback();
                System.out.println("Rollback: ha habido algún error al hacer la compra.");
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

    }
}
