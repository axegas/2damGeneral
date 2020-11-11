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
        view.viewRecordsTable(model);
    }

}
