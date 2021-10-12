package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

// класс, отвечающий за запоминание новых подключений
public class ServerCon {
    public static final int PORT = 8080;
    public static LinkedList<ServerSomething> serverList = new LinkedList<>(); // список всех нитей

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);

        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomething(socket)); // добавление нового соединения в список
                } catch (IOException e){
                    socket.close(); // Если завершится неудачей, закрываем сокет, иначе нить закроет его при завершении работы
                }
            }
        } finally {
          server.close();
        }
    }

}
