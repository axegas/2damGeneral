/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.demo;

import com.projectmusiccrud.controller.ControllerRecord;

/**
 *
 * @author peixe
 */
public class CrudJavaDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ControllerRecord controller = new ControllerRecord();
        controller.selectModel();
    }

}
