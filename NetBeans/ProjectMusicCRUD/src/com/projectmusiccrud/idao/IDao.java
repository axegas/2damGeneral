/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.idao;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author peixe
 */
public interface IDao {

    public boolean insert(Object object);    
    public boolean update(Object object);
    public boolean delete(Object object);
    public DefaultTableModel select();

}
