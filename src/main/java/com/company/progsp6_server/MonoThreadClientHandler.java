package com.company.progsp6_server;

import com.company.progsp6_server.Model.Answer;
import com.company.progsp6_server.Model.Document;
import com.company.progsp6_server.Model.MainModel;
import com.company.progsp6_server.Model.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class MonoThreadClientHandler extends Thread {
    Socket client;
    MainModel mainModel;

    public MonoThreadClientHandler(Socket client, MainModel mainModel) {
        this.client = client;
        this.mainModel = mainModel;
    }

    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            while (true) {
                String inJson = "", outJson = "";
                try {
                    inJson = in.readUTF();
                } catch (IOException e) {
                }
                System.out.println("Клиент прислал: " + inJson);
                Request request = objectMapper.readValue(inJson, Request.class);
                Answer answer = new Answer();
                answer.setAnswer(request.getRequest());
                answer.setDocuments(sendDoc(request));
                outJson = objectMapper.writeValueAsString(answer);
                System.out.println(outJson);
                out.writeUTF(outJson);
                out.flush();
                break;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Document> sendDoc(Request request) throws SQLException {
        ArrayList<Document> documents = new ArrayList<>();
        Document document;
        switch (request.getRequest()) {
            case "get":
                documents = mainModel.getTableViewDocuments();
                break;
            case "add":
                document = request.getDocuments().get(0);
                mainModel.addDocument(document);
                documents = mainModel.getTableViewDocuments();
                break;
            case "edit":
                document = request.getDocuments().get(0);
                mainModel.changeDataDocument(document);
                documents = mainModel.getTableViewDocuments();
                break;
            case "delete":
                document = request.getDocuments().get(0);
                mainModel.deleteDocument(document);
                documents = mainModel.getTableViewDocuments();
                break;
        }
        return documents;
    }
}
