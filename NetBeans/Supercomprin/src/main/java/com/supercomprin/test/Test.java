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
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author axegas
 */
public class Test {

    public static void main(String args[]) throws SQLException {

        try {
            WalletDAO daow = new WalletDAO();
            ProductoDAO daop = new ProductoDAO();
            ArrayList<Wallet> wallets = daow.select();
            ArrayList<Producto> productos = daop.select();
            int opc;
            do {
                opc = menu();
                switch (opc) {
                    case 1:
                        recargasSaldoAleatorias(wallets);
                        break;
                    case 2:
                        comprarConSaldo(wallets, productos);
                        break;
                    case 3:
                        comprarConPuntos(wallets, productos);
                        break;
                    case 4:
                        variasDevoluciones();
                        break;
                }
            } while (opc != 0);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    //para hacer pruebas, he hecho un menú con los métodos principales pedidos en el ejercicio
    public static int menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Recargar saldo a los wallets.");
        System.out.println("2. Unas cuantas compras con saldo aleatorias.");
        System.out.println("3. Unas cuantas compras con puntos aleatorias.");
        System.out.println("4. Unas cuantas devoluciones.");
        System.out.println("0. Salir");
        return scan.nextInt();
    }

    //este método sirve para recargar el saldo de las wallets con un número aleatorio entre 0 y 999
    public static void recargasSaldoAleatorias(ArrayList<Wallet> wallets) throws SQLException {
        Random r = new Random();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < wallets.size(); i++) {
                F.recargarWallet(wallets.get(i), r.nextInt(1000));
            }
        }
    }

    //este método sirve para realizar compras de forma aleatoria con saldo.
    public static void comprarConSaldo(ArrayList<Wallet> wallets, ArrayList<Producto> productos) throws SQLException {
        Random r = new Random();
        try (Connection c = Conexion.getConnection()) {
            WalletDAO daow = new WalletDAO(c);
            for (int i = 0; i < 300; i++) {
                int w = r.nextInt(wallets.size());
                int p = r.nextInt(productos.size());
                Wallet wallet = daow.selectWallet(wallets.get(w).getIdWallet());
                F.pagarCompraConSaldo(wallet, productos.get(p));
            }
        }
    }

    //este método sirve para realizar compras de forma aleatoria con puntos.
    public static void comprarConPuntos(ArrayList<Wallet> wallets, ArrayList<Producto> productos) throws SQLException {
        Random r = new Random();
        try (Connection c = Conexion.getConnection()) {
            WalletDAO daow = new WalletDAO(c);
            for (int i = 0; i < 300; i++) {
                int w = r.nextInt(wallets.size());
                int p = r.nextInt(productos.size());
                Wallet wallet = daow.selectWallet(wallets.get(w).getIdWallet());
                F.pagarCompraConPuntos(wallet, productos.get(p));
            }
        }
    }

    //este método sirve para hacer devoluciones aleatorias
    public static void variasDevoluciones() throws SQLException {
        Random r = new Random();
        try (Connection c = Conexion.getConnection()) {
            CompraDAO daoc = new CompraDAO(c);
            ArrayList<Compra> compras = daoc.select();
            if (compras.isEmpty()) {
                System.out.println("No hay ninguna compra");
                return;
            }
            for (int i = 0; i < 50; i++) {
                int n = r.nextInt(compras.size());
                F.devolverProducto(compras.get(n));
            }
        }
    }
}
