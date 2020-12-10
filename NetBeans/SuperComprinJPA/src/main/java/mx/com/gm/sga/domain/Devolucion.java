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

@Entity
public class Devolucion implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddevolucion;
    private int idcompra;
    private Date fecha;

    public Devolucion() {
    }

    public Devolucion(int iddevolucion, int idcompra, Date fecha) {
        this.iddevolucion = iddevolucion;
        this.idcompra = idcompra;
        this.fecha = fecha;
    }

    public Devolucion(int idcompra, Date fecha) {
        this.idcompra = idcompra;
        this.fecha = fecha;
    }

    public int getiddevolucion() {
        return iddevolucion;
    }

    public void setiddevolucion(int iddevolucion) {
        this.iddevolucion = iddevolucion;
    }

    public int getCompra() {
        return idcompra;
    }

    public void setCompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Devolucion{" + "iddevolucion=" + iddevolucion + ", compra=" + idcompra + ", fecha=" + fecha + '}';
    }

    public int getIddevolucion() {
        return iddevolucion;
    }

    public void setIddevolucion(int iddevolucion) {
        this.iddevolucion = iddevolucion;
    }

}
