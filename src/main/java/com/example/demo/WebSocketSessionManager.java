package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Component
public class WebSocketSessionManager {
    private final Map<String, WebSocketSession> webSocketSessions = new HashMap<>();

    public Collection<WebSocketSession> getWebSocketSessionsExcept(WebSocketSession webSocketSession){
        Map<String, WebSocketSession> nonMatchingSessions = new HashMap<>(webSocketSessions);

        nonMatchingSessions.remove(webSocketSession.getId());
        return nonMatchingSessions.values();
    }

    public void addWebSocketSession(WebSocketSession webSocketSession){
        this.webSocketSessions.put(webSocketSession.getId(), webSocketSession);
    }

    public void removeWebSocketSession(WebSocketSession webSocketSession){
        this.webSocketSessions.remove(webSocketSession.getId(), webSocketSession);
    }
}
