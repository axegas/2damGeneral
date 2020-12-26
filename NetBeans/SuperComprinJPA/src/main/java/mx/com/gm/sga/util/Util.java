/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author peixe
 */
public class Util {
    //funciones auxiliares de SQLDate

    public static Date hoy() {
        java.util.Date fechaJAVA = new java.util.Date();
        Date fechaSQL = new Date(fechaJAVA.getTime());
        return fechaSQL;
    }

    public static Date stringToDate(String strfecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date fechaJAVA = sdf.parse(strfecha);
        Date fechaSQL = new Date(fechaJAVA.getTime());
        return fechaSQL;
    }
}
