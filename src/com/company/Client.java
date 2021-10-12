package com.company;

import javax.swing.plaf.basic.BasicIconFactory;
import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // ридер читающий с консоли
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 4004); // запрашиваем у сервера доступ на соединение

                reader = new BufferedReader(new InputStreamReader(System.in)); // читать сообщения с сервера

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // читать сообщения с сервера

                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); // писать сообщения

                System.out.println("Вы хотели что-то сказать? Введите это здесь: ");
                String word = reader.readLine(); // ждем пока клиент что-нибудь не напишет в консоль

                out.write(word + "\n");
                out.flush();

                String serverWord = in.readLine(); // ждем, что скажет сервнр
                System.out.println(serverWord); // получив - выводим на экран
            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
