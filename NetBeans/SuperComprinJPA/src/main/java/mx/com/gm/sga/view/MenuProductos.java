/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import mx.com.gm.sga.controller.ControllerProductos;

/**
 *
 * @author peixe
 */
public class MenuProductos {

    private static final ControllerProductos control = new ControllerProductos();

    public static void main() throws InputMismatchException {
        int opc;
        do {
            opc = menu();
            switch (opc) {
                case 0:
                    System.out.println("Volvemos al menú principal");
                    break;
                case 1:
                    nuevoProducto();
                    break;
                case 2:
                    borrarProducto();
                    break;
                case 3:
                    mostrarProductos();
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
                    break;
            }
        } while (opc != 0);
    }

    public static int menu() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);

        System.out.println("1. Nuevo Producto.");
        System.out.println("2. Borrar Producto");
        System.out.println("3. Mostrar Productos.");
        System.out.println("0. Volver");
        int opc = scan.nextInt();

        return opc;
    }

    public static void nuevoProducto() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el nombre: ");
        String nombre = scan.nextLine();
        System.out.println("Introduce los puntos: ");
        int puntos = scan.nextInt();
        System.out.println("Introduce el precio: ");
        int precio = scan.nextInt();

        control.nuevoProducto(nombre, puntos, precio);

    }

    public static void mostrarProductos() {
        control.mostrarProductos().forEach(p -> System.out.println(p));
    }

    public static void borrarProducto() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el id: ");
        int id = scan.nextInt();
        control.borrarProducto(id);
    }
}
