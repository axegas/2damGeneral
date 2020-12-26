/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.view;

import java.sql.Date;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import mx.com.gm.sga.controller.ControllerWallets;
import mx.com.gm.sga.util.Util;

/**
 *
 * @author peixe
 */
public class MenuWallets {

    private static final ControllerWallets control = new ControllerWallets();

    public static void main() throws InputMismatchException, ParseException {

        int opc;
        do {
            opc = menu();
            switch (opc) {
                case 0:
                    System.out.println("Volvemos al menú principal");
                    break;
                case 1:
                    nuevaWallet();
                    break;
                case 2:
                    recargarSaldoWallet();
                    break;
                case 3:
                    borrarWallet();
                    break;
                case 4:
                    mostrarWallets();
                    break;
                case 5:
                    control.recargasSaldoAleatorias();
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
                    break;
            }
        } while (opc != 0);
    }

    public static int menu() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);

        System.out.println("1. Nueva Wallet.");
        System.out.println("2. Recargar Saldo");
        System.out.println("3. Borrar Wallet");
        System.out.println("4. Mostrar wallets.");
        System.out.println("5. Recargar saldo a todas las wallets.");
        System.out.println("0. Volver");

        return scan.nextInt();
    }

    public static void nuevaWallet() throws ParseException {
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
        Date fechaNacimiento = Util.stringToDate(fecha);
        long tedad = Util.hoy().getTime() - fechaNacimiento.getTime();
        int edad = (int) (tedad / 31556900000L);
        control.nuevaWallet(nombre, apellidos, dni, fechaNacimiento, email, edad);
    }

    public static void mostrarWallets() {
        control.mostrarWallets().forEach(w -> System.out.println(w));
    }

    public static void borrarWallet() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el id: ");
        int id = scan.nextInt();
        control.borrarWallet(id);
    }

    public static void recargarSaldoWallet() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el id: ");
        int id = scan.nextInt();
        System.out.println("Introduce el saldo a recargar: ");
        float recarga = scan.nextInt();
        control.recargarSaldoWallet(id, recarga);
    }

}
