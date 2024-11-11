package com.company.progsp6_server.Model;

import java.time.LocalDate;

public class Document {
    private int id;
    private String name;
    private int numberOfDoc;
    private String category;
    private String type;
    private String status;
    private LocalDate date;

    public Document() {
    }

    public Document(int id, String name, int numberOfDoc, String category, String type, String status, LocalDate date) {
        this.id = id;
        this.name = name;
        this.numberOfDoc = numberOfDoc;
        this.category = category;
        this.type = type;
        this.status = status;
        this.date = date;
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

    public int getNumberOfDoc() {
        return numberOfDoc;
    }

    public void setNumberOfDoc(int numberOfDoc) {
        this.numberOfDoc = numberOfDoc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
