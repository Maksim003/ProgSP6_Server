package com.company.progsp6_server;

import com.company.progsp6_server.Model.MainModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static MainModel mainModel;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3345);
            System.out.println("Сервер запущен");
            mainModel = new MainModel();
            mainModel.connectToDb();
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread thread = new MonoThreadClientHandler(clientSocket, mainModel);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

