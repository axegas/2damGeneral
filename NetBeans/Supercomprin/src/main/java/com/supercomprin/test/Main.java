/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercomprin.test;

/**
 *
 * @author axegas
 */
public class Main {

    public static void main() {

        Cajero c = new Cajero();

        Thread ta = new Thread(c, "Luis");
        Thread tb = new Thread(c, "Manuel");

        ta.start();
        tb.start();

    }
}

class Persona {

    private int saldo;
    private Cajero c;

    public Persona(Cajero c) {
        this.c = c;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            //saldo = c.getSaldo(50);
        }
    }

}

class Cajero implements Runnable {

    private String nombre;
    private int saldo;

    CuentaBancaria cb = new CuentaBancaria();

    public Cajero() {

    }

    private synchronized int retirarDinero(int saldo) {
        
        
        
        
        
        this.saldo -= saldo;
        return this.saldo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            this.retirarDinero(10);
            

        }
    }

}

class CuentaBancaria{
    
    private int balance = 50;

    public int getBalance(){
        return balance;
    }
    public CuentaBancaria() {

    }
    
    public void retiroBancario(int retiro){
        balance = balance - retiro;
    }
}
