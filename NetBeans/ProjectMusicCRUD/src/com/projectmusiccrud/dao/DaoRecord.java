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
import java.util.ArrayList;
import com.projectmusiccrud.idao.IDao;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author peixe
 */
public class DaoRecord implements IDao {

    private final static String SQL_SELECT = "select * from record";
    private final static String SQL_INSERT = "insert into record (name,composer,year)values(?,?,?)";
    private final static String SQL_UPDATE = "update record set name=?,composer=?,year=? where id=?";
    private final static String SQL_DELETE = "delete from record where id=?";

    @Override
    public ArrayList<Record> select() {
        ArrayList<Record> records = new ArrayList<>();
        Record r;
        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                r = createRecord(rs);
                records.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return records;
    }

    @Override
    public DefaultTableModel selectModel() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] tags = new Object[]{"ID", "NAME", "COMPOSER", "YEAR"};
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

    @Override
    public boolean insert(Object o) {
        Record r = (Record) o;
        boolean insert = false;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, r.getName());
            stmt.setString(2, r.getComposer());
            stmt.setInt(3, r.getYear());
            stmt.executeUpdate();
            insert = true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return insert;
    }

    @Override
    public boolean update(Object o) {
        Record r = (Record) o;
        boolean update = false;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, r.getName());
            stmt.setString(2, r.getComposer());
            stmt.setInt(3, r.getYear());
            stmt.setInt(4, r.getId());
            stmt.executeUpdate();
            update = true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return update;
    }

    @Override
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

    private Record createRecord(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String composer = rs.getString("composer");
        int year = rs.getInt("year");
        Record r = new Record(id, name, composer, year);
        return r;
    }

    private Object[] setRow(Object[] tags, ResultSet rs) throws SQLException {
        Object[] rowData = new Object[tags.length];
        for (int i = 0; i < tags.length; i++) {
            rowData[i] = rs.getObject(i + 1);
        }
        return rowData;
    }
}
