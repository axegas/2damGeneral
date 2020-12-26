/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.view;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author peixe
 */
public class Principal {

    public static void main(String args[]) {

        int opc = -1;
        do {
            try {
                opc = menu();
                switch (opc) {
                    case 0:
                        System.out.println("Gracias por visitar SuperComprin");
                        break;
                    case 1:
                        MenuWallets.main();
                        break;
                    case 2:
                        MenuProductos.main();
                        break;
                    case 3:
                        MenuCompras.main();
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe introducir un número correcto");
            } catch (ParseException e) {
                System.out.println("Fecha incorrecta");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (opc != 0);

    }

    public static int menu() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);

        System.out.println("1. Gestión de wallets.");
        System.out.println("2. Gestión de productos.");
        System.out.println("3. Gestión de compras.");
        System.out.println("0. Salir.");
        return scan.nextInt();
    }
}
