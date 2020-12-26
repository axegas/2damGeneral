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
import mx.com.gm.sga.domain.Wallet;

/**
 *
 * @author axegas
 */
public class ModelWallet {

    public List<Wallet> selectAll() {

        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String str = "Select w from Wallet w";
        Query qr = em.createQuery(str, Wallet.class);
        List<Wallet> wallets = qr.getResultList();

        tx.commit();
        em.clear();

        return wallets;
    }

    public Wallet find(int id) {
        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Wallet w = em.find(Wallet.class, id);

        tx.commit();
        em.clear();
        em.close();

        return w;
    }

    public void insert(Wallet w) {

        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(w);
        
        tx.commit();
        em.clear();
        em.close();
    }

    public void update(Wallet w) {
        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.merge(w);

        tx.commit();
        em.clear();
        em.close();

    }

    public void remove(Wallet w) {
        EntityManager em = Persistence.createEntityManagerFactory("SupercomprinPU").createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        if (!em.contains(w)) {
            w = em.merge(w);
        }

        em.remove(w);
        
        tx.commit();
        em.clear();
        em.close();
    }

}
