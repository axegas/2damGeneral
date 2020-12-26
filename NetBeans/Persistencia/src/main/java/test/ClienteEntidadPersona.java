package test;

import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class ClienteEntidadPersona {

    static Logger log = LogManager.getFormatterLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //iniciamos la transacción
        tx.begin();
        Persona persona1 = new Persona("nombre", "apellidos", "email", 32);
        log.debug("Objeto a persistir: " + persona1);

        //persistimos el objeto
        //em.persist(persona1);
        Persona p = new Persona("juancho");
        Persona p2 = em.find(Persona.class, 5);

        p2.setNombre("juancho");
        em.merge(p2);

        System.out.println(p2);
        //terminamos la transacción
        em.remove(p2);
        p2 = em.find(Persona.class, 5);
        System.out.println(p2);
        tx.commit();
   
        //log.debug("Objeto persistido: " + persona1);
        em.clear();
    }
}
