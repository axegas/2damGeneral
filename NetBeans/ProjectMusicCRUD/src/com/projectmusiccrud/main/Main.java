/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.main;

import com.projectmusiccrud.controller.ControllerRecord;
import com.projectmusiccrud.view.ViewRecordMain;

/**
 *
 * @author peixe
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ViewRecordMain view = new ViewRecordMain(new ControllerRecord());
        view.setVisible(true);
    }

}