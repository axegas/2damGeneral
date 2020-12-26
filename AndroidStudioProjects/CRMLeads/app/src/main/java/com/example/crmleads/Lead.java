package com.example.crmleads;

import java.util.UUID;

public class Lead {
    private String id;
    private String name;
    private String title;
    private String company;
    private int image;

    public Lead(String name, String title, String company, int image) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.title = title;
        this.company = company;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", image=" + image +
                '}';
    }
}
