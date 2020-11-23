/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercomprin.model;

import java.sql.Date;

/**
 *
 * @author axegas
 */
public class Compra {
    
    private int idCompra;
    private Producto producto;
    private Wallet wallet;
    private Date fecha;
    private boolean devuelta;

    public Compra() {
    }

    public Compra(int idCompra, Producto producto, Wallet wallet, boolean devuelta, Date fecha ) {
        this.idCompra = idCompra;
        this.producto = producto;
        this.wallet = wallet;
        this.fecha = fecha;
        this.devuelta = devuelta;
    }

    public Compra(Producto producto, Wallet wallet, Date fecha) {
        this.producto = producto;
        this.wallet = wallet;
        this.fecha = fecha;
        this.devuelta = false;
    }

    public boolean isDevuelta() {
        return devuelta;
    }

    public void setDevuelta(boolean devuelta) {
        this.devuelta = devuelta;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Compra{" + "idCompra=" + idCompra + ", producto=" + producto + ", wallet=" + wallet + ", fecha=" + fecha + ", devuelta=" + devuelta + '}';
    }


    
    
}
