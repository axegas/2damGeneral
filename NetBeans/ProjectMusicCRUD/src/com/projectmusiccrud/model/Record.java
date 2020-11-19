/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectmusiccrud.model;

/**
 *
 * @author peixe
 */
public class Record {
    private int id;
    private String name;
    private String composer;
    private int year;
    private boolean listened;

    public Record() {

    }

    public Record(int id, String name, String composer, int year, boolean listened) {
        this.id = id;
        this.name = name;
        this.composer = composer;
        this.year = year;
        this.listened = listened;
    }

    public Record(String name, String composer, int year, boolean listened) {
        this.name = name;
        this.composer = composer;
        this.year = year;
        this.listened = listened;
    }

    public boolean isListened() {
        return listened;
    }

    public void setListened(boolean listened) {
        this.listened = listened;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Composer: " + composer + ", Year: " + year + '\n';
    }
    
    
}
