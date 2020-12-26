/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.controller;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import mx.com.gm.sga.domain.Wallet;
import mx.com.gm.sga.model.ModelWallet;

/**
 *
 * @author peixe
 */
public class ControllerWallets {

    public static final ModelWallet mWallet = new ModelWallet();

    public void recargasSaldoAleatorias() {
        List<Wallet> wallets = mWallet.selectAll();
        Random r = new Random();

        wallets.forEach(w -> {
            int recarga = r.nextInt(1000);
            w.setSaldo(w.getSaldo() + recarga);

            mWallet.update(w);
        });
    }

    public List<Wallet> mostrarWallets() {
        List<Wallet> wallets = mWallet.selectAll();
        return wallets;
    }

    public void nuevaWallet(String nombre, String apellidos, String dni, Date fechaNacimiento, String email, int edad) {
        Wallet w = new Wallet(nombre, apellidos, dni, fechaNacimiento, email, edad);
        mWallet.insert(w);
    }

    public void borrarWallet(int id) {
        Wallet w = mWallet.find(id);
        mWallet.remove(w);
    }

    public void recargarSaldoWallet(int id, float recarga) throws InputMismatchException {
        Wallet w = mWallet.find(id);
        w.setSaldo(w.getSaldo() + recarga);
        mWallet.update(w);
    }
}
