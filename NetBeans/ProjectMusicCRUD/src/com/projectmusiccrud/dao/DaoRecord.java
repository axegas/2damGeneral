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

    private final static String SQL_SELECT = "select * from record";
    private final static String SQL_INSERT = "insert into record (name,composer,year,listened)values(?,?,?,?)";
    private final static String SQL_UPDATE = "update record set name=?,composer=?,year=?,listened=? where id=?";
    private final static String SQL_DELETE = "delete from record where id=?";

    public DefaultTableModel select() {
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

    public DefaultTableModel selectByName(String name) {
        DefaultTableModel model = new DefaultTableModel() {
            Class[] Columntype = {Integer.class, String.class, String.class, Integer.class, Boolean.class};

            @Override
            public Class getColumnClass(int indColum) {
                return Columntype[indColum];
            }
        };
        Object[] tags = new Object[]{"ID", "NAME", "COMPOSER", "YEAR", "LISTENED"};
        model.setColumnIdentifiers(tags);
        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL_SELECT + " where name='" + name + "'");
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
