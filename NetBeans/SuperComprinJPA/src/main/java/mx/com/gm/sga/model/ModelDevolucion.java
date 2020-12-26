/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import mx.com.gm.sga.domain.Compra;
import mx.com.gm.sga.domain.Devolucion;
import mx.com.gm.sga.domain.Producto;
import mx.com.gm.sga.domain.Wallet;

/**
 *
 * @author axegas
 */
public class ModelDevolucion {

    public void insert(Devolucion d) {

 
        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Compra c = em.find(Compra.class, d.getCompra());
        Producto p = em.find(Producto.class, c.getIdProducto());
        Wallet w = em.find(Wallet.class, c.getIdWallet());
        w.setPuntos(w.getPuntos() - p.getPuntos());
        w.setSaldo(w.getSaldo() + p.getPrecio());
        if (w.getPuntos() >= 5) {
            em.persist(d);
            em.merge(w);
            tx.commit();
        } else {
            tx.rollback();
        }

        em.clear();
        em.close();

    }
}
