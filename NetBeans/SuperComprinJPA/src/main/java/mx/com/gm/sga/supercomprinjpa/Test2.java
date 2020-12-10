/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.supercomprinjpa;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.gm.sga.domain.Producto;
import mx.com.gm.sga.domain.Wallet;

/**
 *
 * @author peixe
 */
public class Test2 {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Supercomprin");
    private static final EntityManager em = emf.createEntityManager();
    private static final EntityTransaction tx = em.getTransaction();

    public static void main(String args[]) {

        tx.begin();
        String qWallets = "Select w from Wallet w";
        String qProductos = "Select p from Producto p";

        Query qrW = em.createQuery(qWallets);
        Query qrP = em.createQuery(qProductos);

        List<Wallet> wallets = (List<Wallet>) qrW.getResultList();
        List<Producto> productos = (List<Producto>) qrW.getResultList();
        tx.commit();

        int opc;
        do {
            opc = menu();
            switch (opc) {
                case 1:
                    recargasSaldoAleatorias(wallets);
                    break;
//                 case 2:
//                    comprarConSaldo(wallets, productos);
//                    break;
//                case 3:
//                    comprarConPuntos(wallets, productos);
//                    break;
//                case 4:
//                    variasDevoluciones();
//                    break;
                case 5:
                    nuevaWallet(wallets);
                    break;
                case 6:
                    nuevoProducto(productos);
                    break;
            }
        } while (opc != 0);

    }

    public static int menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Recargar saldo a los wallets.");
        System.out.println("2. Unas cuantas compras con saldo aleatorias.");
        System.out.println("3. Unas cuantas compras con puntos aleatorias.");
        System.out.println("4. Unas cuantas devoluciones.");
        System.out.println("5. Nueva Wallet.");
        System.out.println("6. Nuevo producto.");
        System.out.println("0. Salir");
        return scan.nextInt();
    }

    public static void recargasSaldoAleatorias(List<Wallet> wallets) {
        Random r = new Random();
        wallets.forEach(wallet -> {
            int recarga = r.nextInt(1000);
            wallet.setSaldo(wallet.getSaldo() + recarga);
            tx.begin();
            em.merge(wallet);
            tx.commit();
        });
    }

    private static void nuevoProducto(List<Producto> productos) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el nombre: ");
        String nombre = scan.nextLine();
        System.out.println("Introduce los puntos: ");
        int puntos = scan.nextInt();
        System.out.println("Introduce el precio: ");
        int precio = scan.nextInt();

        Producto p = new Producto(nombre, puntos, precio);
        productos.add(p);
        tx.begin();
        em.persist(p);
        tx.commit();
    }

    private static void nuevaWallet(List<Wallet> wallets) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el nombre: ");
        String nombre = scan.nextLine();
        System.out.println("Introduce los apellidos: ");
        String apellidos = scan.nextLine();
        System.out.println("Introduce el DNI:");
        String dni = scan.nextLine();
        System.out.println("Introduce la fecha de nacimiento (formato dd-mm-aaaa):");
        String fecha = scan.nextLine();
        System.out.println("Introduce el e-mail:");
        String email = scan.nextLine();
        Date fechaNacimiento = getFecha(fecha);

        Date now = getNow();
        long tnow = getNow().getTime();
        long tfecha = fechaNacimiento.getTime();
        long tedad = tnow - tfecha;
        int edad = (int) (tedad / 31556900000L);

        Wallet w = new Wallet(nombre, apellidos, dni, fechaNacimiento, email, edad);
        wallets.add(w);
        tx.begin();
        em.persist(w);
        tx.commit();
    }

    public static Date getNow() {
        java.util.Date fechaJAVA = new java.util.Date();
        Date fechaSQL = new Date(fechaJAVA.getTime());
        return fechaSQL;
    }

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
