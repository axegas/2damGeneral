package clases;


import java.io.Serializable;

public class User implements Serializable {

    private String nick;
    private String ip;

    public User(String nick, String ip) {
        this.nick = nick;
        this.ip = ip;
    }

    public User(String ip) {
        this.ip = ip;
        this.nick = "";
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNicks() {
        return nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return nick;
    }

}
