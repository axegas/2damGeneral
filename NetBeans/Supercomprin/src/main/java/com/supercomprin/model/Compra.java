/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercomprin.model;

/**
 *
 * @author axegas
 */
public class Compra {
    
    private int idCompra;
    private Producto producto;
    private Wallet wallet;
    private String fecha;

    public Compra() {
    }

    public Compra(int idCompra, Producto producto, Wallet wallet, String fecha) {
        this.idCompra = idCompra;
        this.producto = producto;
        this.wallet = wallet;
        this.fecha = fecha;
    }

    public Compra(Producto producto, Wallet wallet, String fecha) {
        this.producto = producto;
        this.wallet = wallet;
        this.fecha = fecha;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Compra{" + "idCompra=" + idCompra + ", producto=" + producto + ", wallet=" + wallet + ", fecha=" + fecha + '}';
    }
    
    
}
