
package mx.com.gm.sga.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Persona implements Serializable{
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idpersona;
    private String nombre;
    private String apellidos;
    private int edad;
    private String email;    
    
    public Persona() {
    }
    public Persona(String nombre) {
        this.nombre = nombre;
    }
    

    public Persona(String nombre, String apellidos, String email, int edad) {
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public Persona(int idpersona, String nombre, String apellidos, String email, int edad) {
        this.idpersona = idpersona;
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + idpersona + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + '}';
    }
}
