/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.gm.sga.domain.Contacto;

/**
 *
 * @author axegas
 */
public class GestionContactos {

    public List<Contacto> recuperarContactos() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        String jsql = "Select c from Contacto c";
        Query qr = em.createQuery(jsql, Contacto.class);
        List<Contacto> contactos = qr.getResultList();

        tx.commit();

        em.clear();
        em.close();
        return contactos;
    }

    public List<Contacto> buscarContacto(String email) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        String jsql = "Select c from Contacto c where c.email = " + email;
        Query qr = em.createQuery(jsql, Contacto.class);
        List<Contacto> contactos = qr.getResultList();

        tx.commit();
        em.clear();
        em.close();
        return contactos;
    }

    public Contacto buscarContacto2(String email) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        String jsql = "Select c from Contacto c where c.email = " + email;
        Query qr = em.createQuery(jsql, Contacto.class);
        Contacto c = (Contacto) qr.getSingleResult();

        tx.commit();
        em.clear();
        em.close();
        return c;
    }

    public void altaContacto(String nombre, String email, int telefono) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Contacto contacto = new Contacto(nombre, email, telefono);
        em.persist(contacto);

        tx.commit();
        em.clear();
        em.close();
    }

    public void eliminarContacto(int idContacto) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Contacto contacto = em.find(Contacto.class,idContacto);
        em.remove(contacto);

        tx.commit();
        em.clear();
        em.close();
    }
}
