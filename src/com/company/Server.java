package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in; // потоки чтения из сокета
    private static BufferedWriter out; // потоки записи в сокет

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4004);
                System.out.println("Сервер запущен!");

                clientSocket = server.accept(); // accept будет ждать пока кто-нибудь не захочет подключиться
                try { // здесь мы уже установили связь и воссоздали сокет для общения с клиентом
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // принимаем сообщения
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); // отправляем сообщения

                    String word = in.readLine(); // ждем пока клиент что-нибудь нам напишет
                    System.out.println(word);

                    out.write("Привет, это сервер! Подтверждаю, вы написали: " + word + "\n"); // отвечаем клиенту
                    out.flush(); // выталкиваем все из буфера
                } finally { // закрываем все потоки
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        }
        catch (IOException e){
            System.err.println(e);
        }
    }
}
