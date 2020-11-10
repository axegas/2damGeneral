/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.idao;

import com.projectmusiccrud.model.Record;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author peixe
 */
public interface IDao {

    public boolean insert(Object object);

    public ArrayList<Record> select();

    public boolean update(Object object);

    public boolean delete(Object object);

    public DefaultTableModel selectModel();

}
