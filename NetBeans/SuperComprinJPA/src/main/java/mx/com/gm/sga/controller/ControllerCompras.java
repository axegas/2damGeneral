/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.controller;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import mx.com.gm.sga.domain.Compra;
import mx.com.gm.sga.domain.Devolucion;
import mx.com.gm.sga.domain.Producto;
import mx.com.gm.sga.domain.Wallet;
import mx.com.gm.sga.model.ModelCompra;
import mx.com.gm.sga.model.ModelDevolucion;
import mx.com.gm.sga.model.ModelProducto;
import mx.com.gm.sga.model.ModelWallet;
import mx.com.gm.sga.util.Util;

/**
 *
 * @author peixe
 */
public class ControllerCompras {

    public static final ModelDevolucion mDevolucion = new ModelDevolucion();
    public static final ModelCompra mCompra = new ModelCompra();
    public static final ModelWallet mWallet = new ModelWallet();
    public static final ModelProducto mProducto = new ModelProducto();

    public List<Compra> mostrarCompras() {
        return mCompra.selectAll();
    }

    public void devolverCompra(int idcompra, Date fechaHoy) {
        Devolucion d = new Devolucion(idcompra, fechaHoy);
        mDevolucion.insert(d);
    }

    public void comprar(int idproducto, int idwallet, Date fecha, String metodo) {
        Compra c = new Compra(idproducto, idwallet, fecha);
        if (metodo.equals("saldo")) {
            mCompra.insertSaldo(c);
        } else {
            mCompra.insertPuntos(c);
        }
    }

    public void comprasAleatorias() {
        Random r = new Random();
        List<Wallet> wallets = mWallet.selectAll();
        List<Producto> productos = mProducto.selectAll();

        for (int i = 0; i < 100; i++) {
            int w = r.nextInt(wallets.size());
            int p = r.nextInt(productos.size());
            Wallet wallet = wallets.get(w);
            Producto producto = productos.get(p);
            Compra c = new Compra(producto.getIdproducto(), wallet.getIdwallet(), Util.hoy());
            mCompra.insertSaldo(c);
        }
    }
}
