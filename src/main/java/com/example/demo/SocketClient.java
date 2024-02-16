package com.example.demo;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        // connection url
        String uri = "ws://localhost:8080/user";

        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketSession session = null;
        try {
            // Определеям обработчик событий
            SocketTextHandler socket = new SocketTextHandler();
            // Рукопожатие с сервером
            session = client.execute(socket, uri).get();
            // Отправляем сообщение
            session.sendMessage(new TextMessage("Hello Im Here"));
            // Закрываем соединение
            new Scanner(System.in).nextLine();
            session.close();

        } catch (Throwable t) {
            t.printStackTrace(System.err);
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (IOException ignored) {
            }
        }
    }
}