/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Date;

/**
 *
 * @author axegas
 */
public class User {

    private int id;
    private String nick;
    private String password;
    private Date fecha;

    public User(int id, String nick, String password, Date fecha) {
        this.id = id;
        this.nick = nick;
        this.password = password;
        this.fecha = fecha;
    }

    public User(String nick, String password, Date fecha) {
        this.nick = nick;
        this.password = password;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public User() {
    }

    public User(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nick=" + nick + ", password=" + password + '}';
    }

}
