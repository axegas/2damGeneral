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
import mx.com.gm.sga.domain.Producto;

/**
 *
 * @author axegas
 */
public class ModelProducto {

    public List<Producto> selectAll() {

        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String str = "Select p from Producto p";
        Query qr = em.createQuery(str, Producto.class);
        List<Producto> productos = qr.getResultList();

        tx.commit();
        em.clear();
        em.close();

        return productos;

    }

    public Producto find(int id) {
        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Producto p = em.find(Producto.class, id);

        tx.commit();
        em.clear();
        em.close();

        return p;
    }

    public void insert(Producto p) {

        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(p);

        tx.commit();
        em.clear();
        em.close();
    }

    public void remove(Producto p) {
        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        if (!em.contains(p)) {
            p = em.merge(p);
        }

        em.remove(p);

        tx.commit();
        em.clear();
        em.close();
    }
}
