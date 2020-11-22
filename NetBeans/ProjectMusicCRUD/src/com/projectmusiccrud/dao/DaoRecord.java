/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.dao;

import com.connection.Conexion;
import com.projectmusiccrud.model.Record;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author peixe
 */
public class DaoRecord {

    //sentencias SQL preparadas
    private final static String SQL_SELECT = "select * from record";
    private final static String SQL_INSERT = "insert into record (name,composer,year,listened)values(?,?,?,?)";
    private final static String SQL_UPDATE = "update record set name=?,composer=?,year=?,listened=? where id=?";
    private final static String SQL_DELETE = "delete from record where id=?";

    //metodo select. devuelve el tablemodel para formatear la jtable con los datos obtenidos de la base de datos
    public DefaultTableModel select() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {//para que las filas no sean editables
                return false;
            }
            Class[] Columntype = {Integer.class, String.class, String.class, Integer.class, Boolean.class};

            @Override
            public Class getColumnClass(int indColum) {//para poder hacer que la ultima columna sea un check
                return Columntype[indColum];
            }
        };
        Object[] tags = new Object[]{"ID", "NAME", "COMPOSER", "YEAR", "LISTENED"};//cabeceras de la jtable
        model.setColumnIdentifiers(tags);
        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Object[] rowData = setRow(tags, rs);
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return model;
    }

    //a partir de los datos de los objetos, devuelve el tablemodel con el resultado de la busqueda
    public DefaultTableModel specificSearch(String name, String composer, int year, int listened) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
            Class[] Columntype = {Integer.class, String.class, String.class, Integer.class, Boolean.class};

            @Override
            public Class getColumnClass(int indColum) {
                return Columntype[indColum];
            }
        };
        Object[] tags = new Object[]{"ID", "NAME", "COMPOSER", "YEAR", "LISTENED"};
        model.setColumnIdentifiers(tags);

        //a partir de aquí, construyo el Where
        String nameStr = "";
        String composerStr = "";
        String where = "";
        String listenedStr = "";
        if (year > 0) {
            where += " year = " + year;
        }
        if (name.length() > 0) {
            nameStr += " name like '%" + name + "%'";
            if (where.length() > 0) {
                where += " and " + nameStr;
            } else {
                where += nameStr;
            }
        }
        if (composer.length() > 0) {
            composerStr += " composer like '%" + composer + "%'";
            if (where.length() > 0) {
                where += " and " + composerStr;
            } else {
                where += composerStr;
            }
        }

        if (listened != 2) {
            listenedStr += " listened = " + listened;
            if (where.length() > 0) {
                where += " and " + listenedStr;
            } else {
                where += listenedStr;
            }
        }

        if (where.length() > 0) {
            where = " where " + where;
        }

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL_SELECT + where);//una vez tengo el where construido, lo añado a la busqueda
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Object[] rowData = setRow(tags, rs);
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return model;
    }

    public boolean insert(Object o) {
        Record r = (Record) o;
        boolean insert = false;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, r.getName());
            stmt.setString(2, r.getComposer());
            stmt.setInt(3, r.getYear());
            stmt.setBoolean(4, r.isListened());
            stmt.executeUpdate();
            insert = true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return insert;
    }

    public boolean update(Object o) {
        Record r = (Record) o;
        boolean update = false;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, r.getName());
            stmt.setString(2, r.getComposer());
            stmt.setInt(3, r.getYear());
            stmt.setBoolean(4, r.isListened());
            stmt.setInt(5, r.getId());
            stmt.executeUpdate();
            update = true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return update;
    }

    public boolean delete(Object o) {
        Record r = (Record) o;
        boolean delete = false;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {
            stmt.setInt(1, r.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return delete;
    }

    //a partir del rs, creo cada fila de datos, para insertarla posteriormente en el tablemodel
    private Object[] setRow(Object[] tags, ResultSet rs) throws SQLException {
        Object[] rowData = new Object[tags.length];
        for (int i = 0; i < tags.length - 1; i++) {
            rowData[i] = rs.getObject(i + 1);
        }
        int b = rs.getInt(tags.length);
        rowData[tags.length - 1] = 1 == b;
        return rowData;
    }
}
