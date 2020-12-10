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
public class Compra implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcompra;
    private int idproducto;
    private int idwallet;
    private Date fecha;

    public Compra() {
    }

    public Compra(int idcompra, int idproducto, int idwallet, Date fecha) {
        this.idcompra = idcompra;
        this.idproducto = idproducto;
        this.idwallet = idwallet;
        this.fecha = fecha;
    }

    public Compra(int idproducto, int idwallet, Date fecha) {
        this.idproducto = idproducto;
        this.idwallet = idwallet;
        this.fecha = fecha;
    }

    public int getIdCompra() {
        return idcompra;
    }

    public void setIdCompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getProducto() {
        return idproducto;
    }

    public void setProducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getWallet() {
        return idwallet;
    }

    public void setWallet(int idwallet) {
        this.idwallet = idwallet;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Compra{" + "idCompra=" + idcompra + ", producto=" + idproducto + ", wallet=" + idwallet + ", fecha=" + fecha + '}';
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

}
