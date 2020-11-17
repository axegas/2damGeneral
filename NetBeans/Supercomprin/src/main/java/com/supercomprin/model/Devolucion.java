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
    private Compra compra;
    private Date fecha;

    public Devolucion() {
    }

    public Devolucion(int iddevolucion, Compra compra, Date fecha) {
        this.iddevolucion = iddevolucion;
        this.compra = compra;
        this.fecha = fecha;
    }

    public Devolucion(Compra compra, Date fecha) {
        this.compra = compra;
        this.fecha = fecha;
    }

    public int getiddevolucion() {
        return iddevolucion;
    }

    public void setiddevolucion(int iddevolucion) {
        this.iddevolucion = iddevolucion;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Devolucion{" + "iddevolucion=" + iddevolucion + ", compra=" + compra + ", fecha=" + fecha + '}';
    }

}
