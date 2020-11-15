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
public class Producto {
    private int idproducto;
    private String nombre;
    private int puntos;
    private int precio;

    public Producto() {
    }

    public Producto(int idproducto, String nombre, int puntos, int precio) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.puntos = puntos;
        this.precio = precio;
    }

    public Producto(String nombre, int puntos, int precio) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.precio = precio;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idproducto + ", nombre=" + nombre + ", puntos=" + puntos + ", precio=" + precio + "}\n";
    }
    
    
}
