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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author axegas
 */
public class Test {

    public static void main(String args[]) {
        try {
            Random r = new Random();
            CompraDAO daoc = new CompraDAO(Conexion.getConnection());
            WalletDAO daow = new WalletDAO(Conexion.getConnection());
            ProductoDAO daop = new ProductoDAO(Conexion.getConnection());

            ArrayList<Wallet> wallets = daow.select();
            ArrayList<Producto> productos = daop.select();

            //Unas cuantas recargas de saldo
//            for (int i = 0; i < wallets.size(); i++) {
//                F.recargarWallet(wallets.get(i), r.nextInt(1000));
//            }

            //recargo los wallets con el saldo actualizado despues de recargar
            wallets = daow.select();
            
            //unas cuantas compras de prueba - con saldo
//            for (int i = 0; i < 200; i++) {
//                int w = r.nextInt(wallets.size());
//                int p = r.nextInt(productos.size());
//                F.pagarCompraConSaldo(wallets.get(w), productos.get(p));
//            }

            //recargo los wallets con el saldo actualizado despues de comprar
            wallets = daow.select();

            //unas cuantas compras de prueba - con puntos
            for (int i = 0; i < 1; i++) {
                int w = r.nextInt(wallets.size());
                int p = r.nextInt(productos.size());
                System.out.println(wallets.get(w));
                System.out.println( productos.get(p));
                F.pagarCompraConPuntos(wallets.get(w), productos.get(p));
                
            }

/*
            //Seleccionamos las compras que hemos realizado
            ArrayList<Compra> compras = daoc.select();

            //devolvemos algunas compras aleatorias
            for (int i = 0; i < 20; i++) {
                int n = r.nextInt(compras.size());
                F.devolverProducto(compras.get(n));
            }
     */      
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
