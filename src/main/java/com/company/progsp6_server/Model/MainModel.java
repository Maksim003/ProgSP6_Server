package com.company.progsp6_server.Model;

import com.company.progsp6_server.Database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainModel {
    Database db = new Database();

    public void connectToDb() {
        db.createDbConnection();
    }

    public void addDocument(Document document) {
        String insertFields = "INSERT INTO documents(name, number, category, type, status, date) VALUES ('";
        String insertValues = document.getName() + "','" + document.getNumberOfDoc() + "','" + document.getCategory() + "','" + document.getType() +
                "','" + document.getStatus() + "','" + document.getDate() + "')";
        String insert = insertFields + insertValues;
        db.updateData(insert);
    }

    public void deleteDocument(Document document) {
        String select = "DELETE FROM documents" + " WHERE id = " + "\"" + document.getId() + "\"";
        db.updateData(select);
    }

    public void changeDataDocument(Document document) {
        String select = "UPDATE documents SET name = " + "\"" + document.getName() + "\"" + " , number = " + document.getNumberOfDoc() + " , " +
                "category = " + "\"" + document.getCategory() + "\"" + " , type = " + "\"" + document.getType() + "\"" + " , status = " +"\""+ document.getStatus() +"\"" +
                " , date = " + "\"" + document.getDate() + "\"" + " WHERE id = " + document.getId();
        db.updateData(select);
    }

    public ArrayList<Document> getTableViewDocuments() throws SQLException {
        ArrayList<Document> documents = new ArrayList<>();
        String select = "SELECT * FROM documents";
        ResultSet resultSet = db.getData(select);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int number = resultSet.getInt("number");
            String category = resultSet.getString("category");
            String type = resultSet.getString("type");
            String status = resultSet.getString("status");
            LocalDate date = resultSet.getDate("date").toLocalDate();
            documents.add(new Document(id, name, number, category, type, status, date));
        }
        return documents;
    }
}
