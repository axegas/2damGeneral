/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.controller;

import com.projectmusiccrud.dao.RecordDaoImpl;
import com.projectmusiccrud.model.Record;
import com.projectmusiccrud.view.ViewRecord;
import com.projectmusiccrud.idao.IDao;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author peixe
 */
public class ControllerRecord {

    private final ViewRecord view;

    public ControllerRecord() {
        view = new ViewRecord();
        view.setVisible(true);
    }

    public void insert(Record record) {
        IDao dao = new RecordDaoImpl();
        dao.insert(record);
    }

    public void update(Record record) {
        IDao dao = new RecordDaoImpl();
        dao.update(record);
    }

    public void delete(Record record) {
        IDao dao = new RecordDaoImpl();
        dao.delete(record);
    }

    public void selectModel() {
        IDao dao = new RecordDaoImpl();
        DefaultTableModel model = dao.selectModel();
        Object[] tags = new Object[]{"ID", "NAME", "COMPOSER", "YEAR"};
        model.setColumnIdentifiers(tags);
        view.viewRecordsTable(model);
    }

    public void selectModelArray() {
        IDao dao = new RecordDaoImpl();
        ArrayList<Record> records = dao.select();
        Object[] tags = new Object[]{"ID", "NAME", "COMPOSER", "YEAR"};
        DefaultTableModel model = arrayToModel(records);
        model.setColumnIdentifiers(tags);
        view.viewRecordsTable(model);
    }

    private DefaultTableModel arrayToModel(ArrayList<Record> records) {
        DefaultTableModel model = new DefaultTableModel();
        
        for (int i = 0; i < records.size(); i++) {
            Object[] fila = new Object[4];
            fila[0] = records.get(i).getId();
            fila[1] = records.get(i).getName();
            fila[2] = records.get(i).getComposer();
            fila[3] = records.get(i).getYear();
            model.addRow(fila);
        }
        return model;
    }

}
