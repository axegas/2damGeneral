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
public class Devolucion {

    private int iddevolucion;
    private Producto producto;
    private Wallet wallet;
    private Date fecha;

    public Devolucion() {
    }

    public Devolucion(int iddevolucion, Producto producto, Wallet wallet, Date fecha) {
        this.iddevolucion = iddevolucion;
        this.producto = producto;
        this.wallet = wallet;
        this.fecha = fecha;
    }

    public Devolucion(Producto producto, Wallet wallet, Date fecha) {
        this.producto = producto;
        this.wallet = wallet;
        this.fecha = fecha;
    }

    public int getiddevolucion() {
        return iddevolucion;
    }

    public void setiddevolucion(int iddevolucion) {
        this.iddevolucion = iddevolucion;
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
        return "Compra{" + "iddevolucion=" + iddevolucion + ", producto=" + producto + ", wallet=" + wallet + ", fecha=" + fecha + '}';
    }
}
