/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.projectmusiccrud.dao.DaoRecord;
import com.projectmusiccrud.model.Record;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author peixe
 */
public class ControllerRecord {

    private final DaoRecord dao;

    public ControllerRecord(DaoRecord dao) {
        this.dao = dao;
    }

    public void insert(Record record) {
        dao.insert(record);
    }

    public void update(Record record) {
        dao.update(record);
    }

    public void delete(Record record) {
        dao.delete(record);
    }

    public DefaultTableModel selectModel() {
        DefaultTableModel model = dao.select();
        return model;
    }

    public void createPDF(ArrayList<Record> records) {
        try {
            String text = "Record List:\n\n";

            for (Record r : records) {
                text += r;
            }

            JFileChooser f = new JFileChooser();
            f.showSaveDialog(null);

            String fichero = f.getSelectedFile().toString();
            if(!fichero.endsWith(".pdf")){
                fichero += ".pdf";
            }

            Document doc = new Document(PageSize.A4, 50, 50, 100, 72);
            PdfWriter.getInstance(doc, new FileOutputStream(fichero));
            doc.open();
            Paragraph p = new Paragraph(text);
            p.setAlignment(Element.ALIGN_JUSTIFIED);

            doc.add(p);
            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
}