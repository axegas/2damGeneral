/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.controller;

import java.util.List;
import mx.com.gm.sga.domain.Producto;
import mx.com.gm.sga.model.ModelProducto;

/**
 *
 * @author peixe
 */
public class ControllerProductos {

    public static final ModelProducto mProducto = new ModelProducto();

    public List<Producto> mostrarProductos() {
        List<Producto> productos = mProducto.selectAll();
        return productos;
    }

    public void nuevoProducto(String nombre, int puntos, int precio) {
        Producto p = new Producto(nombre, puntos, precio);
        mProducto.insert(p);
    }

    public void borrarProducto(int id) {
        Producto p = mProducto.find(id);
        mProducto.remove(p);
    }

}
