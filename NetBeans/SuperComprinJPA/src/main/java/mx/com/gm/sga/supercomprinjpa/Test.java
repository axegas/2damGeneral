package mx.com.gm.sga.supercomprinjpa;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import mx.com.gm.sga.domain.*;
import org.apache.logging.log4j.*;

public class Test {

    static Logger log = LogManager.getFormatterLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Supercomprin");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //iniciamos la transacción
        //tx.begin();
        
        //log.debug("Objeto a persistir: " + persona1);
        Date now = getNow();        
        Date fechanac = getFecha("28-06-1988");
        long tnow = getNow().getTime();
        long tfecha = fechanac.getTime();
        long tedad = tnow - tfecha;
        int tanyos = (int) (tedad/31556900000L);
        
        System.out.println(tanyos);
        Wallet w = new Wallet("nombre", "apellidos", "dni",fechanac,"email",32);
        String q = "Select w from Devolucion w";
        //Query qr = em.createQuery(q);
        
        //List<Devolucion> wallets = (List<Devolucion>) qr.getResultList();
                
        //wallets.forEach(wa -> System.out.println(wa));
        
        //public Wallet(String nombre, String apellidos, String dni, Date fechanacimiento, String email) {
        //em.persist(w);
        //persistimos el objeto
        //em.persist(persona1);
        //Persona p = new Persona("juancho");
        //Persona p2 = em.find(Persona.class, 5);
        //Wallet w2 = em.find(Wallet.class, 13);
        //p2.setNombre("juancho");
        //em.merge(p2);
        //System.out.println(w2);
        //System.out.println(p2);
        //terminamos la transacción
        //em.remove(w2);
        //p2 = em.find(Persona.class, 5);
        //System.out.println(p2);
        //tx.commit();
   
        //log.debug("Objeto persistido: " + persona1);
        //em.clear();
    }
    
        //estos dos métodos los he tenido que implementar dada la poca compatibilidad entre el Date de java.util y el Date de java.sql
    //el primero devuelve la hora actual (para realizar las compras/devoluciones) en formato sql.date
    public static Date getNow() {
        java.util.Date fechaJAVA = new java.util.Date();
        Date fechaSQL = new Date(fechaJAVA.getTime());
        return fechaSQL;
    }

    //el siguiente devuelve, a partir de un string en formato "dd-MM-yyyy", el objeto de tipo sql.date
    public static Date getFecha(String strfecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaSQL = null;
        try {
            java.util.Date fechaJAVA = sdf.parse(strfecha);
            fechaSQL = new Date(fechaJAVA.getTime());
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }
        return fechaSQL;
    }
}
