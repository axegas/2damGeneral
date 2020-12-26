/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.gm.sga.domain.Compra;
import mx.com.gm.sga.domain.Producto;
import mx.com.gm.sga.domain.Wallet;

/**
 *
 * @author axegas
 */
public class ModelCompra {

    public List<Compra> selectAll() {

        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String str = "Select c from Compra c";
        Query qr = em.createQuery(str, Compra.class);
        List<Compra> compras = qr.getResultList();

        tx.commit();
        em.clear();
        em.close();

        return compras;
    }

    public void insertSaldo(Compra c) {

        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Producto p = em.find(Producto.class, c.getIdProducto());
        Wallet w = em.find(Wallet.class, c.getIdWallet());
        w.setSaldo(w.getSaldo() - p.getPrecio());
        w.setPuntos(w.getPuntos() + p.getPuntos());
        em.persist(c);
        em.merge(w);
        tx.commit();
        em.clear();
        em.close();
    }

    public void insertPuntos(Compra c) {
        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Producto p = em.find(Producto.class, c.getIdProducto());
        Wallet w = em.find(Wallet.class, c.getIdWallet());
        w.setPuntos((int) (w.getPuntos() - p.getPrecio()));
        if (p.getPrecio() >= 5) {
            em.persist(c);
            em.merge(w);
            tx.commit();
        } else {
            tx.rollback();            
        }

        em.clear();
        em.close();
    }
}
