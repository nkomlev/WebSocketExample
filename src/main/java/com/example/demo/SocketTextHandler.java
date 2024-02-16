package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class SocketTextHandler extends TextWebSocketHandler {
   private WebSocketSessionManager webSocketSessionManager = new WebSocketSessionManager();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Обработка подключения
        webSocketSessionManager.addWebSocketSession(session);
        System.out.println(session.getId() + " connected");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // Обработка разрыва подключения
        webSocketSessionManager.removeWebSocketSession(session);
        System.out.println(session.getId() + " disconnected");
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Обработка получения сообщения
        String payload = message.getPayload();
        System.out.println(payload);
        webSocketSessionManager.getWebSocketSessionsExcept(session).forEach(webSocketSession -> {
            try {
                webSocketSession.sendMessage(new TextMessage("message = " + payload));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
