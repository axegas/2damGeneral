/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author peixe
 */
@Entity
public class Wallet implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idwallet;
    private String nombre;
    private String apellidos;
    private String dni;
    private Date fechanacimiento;
    private String email;
    private int puntos;
    private float saldo;
    private int edad;

    //constructor para insertar
    public Wallet(String nombre, String apellidos, String dni, Date fechanacimiento, String email, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechanacimiento = fechanacimiento;
        this.email = email;
        this.puntos = 0;
        this.saldo = 0;
        this.edad = edad;
    }

    public Wallet() {
    }

    public Wallet(int idwallet, String nombre, String apellidos, String dni, Date fechanacimiento, String email, int puntos, float saldo, int edad) {
        this.idwallet = idwallet;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechanacimiento = fechanacimiento;
        this.email = email;
        this.puntos = puntos;
        this.saldo = saldo;
        this.edad = edad;
    }

    public int getIdwallet() {
        return idwallet;
    }

    public void setIdwallet(int idwallet) {
        this.idwallet = idwallet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "idwallet: " + idwallet + ", nombre: " + nombre + ", apellidos: " + apellidos + ", dni: " + dni + ", fechanacimiento: " + fechanacimiento + ", email: " + email + ", puntos: " + puntos + ", saldo: " + saldo + ", edad: " + edad;
    }

}
