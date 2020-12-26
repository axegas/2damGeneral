/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.view;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import mx.com.gm.sga.controller.ControllerCompras;
import mx.com.gm.sga.util.Util;

/**
 *
 * @author peixe
 */
public class MenuCompras {

    private static final ControllerCompras control = new ControllerCompras();
    public static void main() throws InputMismatchException, ParseException {

        int opc;
        do {
            opc = menu();
            switch (opc) {
                case 0:
                    System.out.println("Volvemos al menú principal");
                    break;
                case 1:
                    comprar("puntos");
                    break;
                case 2:
                    comprar("saldo");
                    break;
                case 3:
                    devolverCompra();
                    break;
                case 4:
                    mostrarCompras();
                    break;
                case 5:
                    control.comprasAleatorias();
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
                    break;
            }
        } while (opc != 0);
    }

    public static int menu() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);

        System.out.println("1. Comprar con puntos.");
        System.out.println("2. Comprar con saldo");
        System.out.println("3. Devolver compra.");
        System.out.println("4. Mostrar compras");
        System.out.println("5. Compras aleatorias");
        System.out.println("0. Volver");
        int opc = scan.nextInt();

        return opc;
    }

    public static void mostrarCompras() {
        control.mostrarCompras().forEach(c -> System.out.println(c));
    }

    public static void devolverCompra() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el ID de la compra: ");
        int idcompra = scan.nextInt();
        control.devolverCompra(idcompra, Util.hoy());
    }

    public static void comprar(String metodo) throws InputMismatchException {

        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el ID del producto: ");
        int idproducto = scan.nextInt();
        System.out.println("Introduce el ID del wallet: ");
        int idwallet = scan.nextInt();
        control.comprar(idproducto, idwallet, Util.hoy(), metodo);
    }



}
