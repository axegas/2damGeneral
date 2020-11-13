/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import domain.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author axegas
 */
public class PersonaDAO {

    private final static String SQL_SELECT = "select * from persona";
    private final static String SQL_INSERT = "insert into persona(nombre,apellidos,email,edad)values(?,?,?,?)";
    private final static String SQL_UPDATE = "update persona set nombre=?,apellidos=?,email=?,edad=? where id_persona=?";
    private final static String SQL_DELETE = "delete from persona where id_persona=?";
    private Connection conexionTransaccional;

    public PersonaDAO() {
    }

    public PersonaDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public ArrayList<Persona> select() throws SQLException {
        ArrayList<Persona> personas = new ArrayList<>();
        Persona p;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                p = crearPersona(rs);
                personas.add(p);
            }
        }finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return personas;
    }

    public int insert(Persona p) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getApellidos());
            stmt.setString(3, p.getEmail());
            stmt.setInt(4, p.getEdad());
            registros = stmt.executeUpdate();
        } finally {
            try {
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int update(Persona p) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getApellidos());
            stmt.setString(3, p.getEmail());
            stmt.setInt(4, p.getEdad());
            stmt.setInt(5, p.getId());
            registros = stmt.executeUpdate();
        } finally {
            try {
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int delete(Persona p) throws SQLException {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, p.getId());
            registros = stmt.executeUpdate();
        }  finally {
            try {
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    private Persona crearPersona(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_persona");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        String email = rs.getString("email");
        int edad = rs.getInt("edad");
        Persona p = new Persona(id, nombre, apellidos, email, edad);
        return p;
    }

}
