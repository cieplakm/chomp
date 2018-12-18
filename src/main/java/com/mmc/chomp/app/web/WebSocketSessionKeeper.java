package com.mmc.chomp.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
class WebSocketSessionKeeper {
    private Map<String, WebSocketSession> map = new HashMap<>();

    public void add(WebSocketSession webSocketSession) {
        String userId = getUserId(webSocketSession);
        map.put(userId, webSocketSession);
        log.info("Session {} saved for user {}", webSocketSession.getId(), userId);
    }

    public void remove(WebSocketSession webSocketSession) {
        String userId = getUserId(webSocketSession);
        map.remove(userId);
        log.info("Session {} removed for user {}", webSocketSession.getId(), userId);
    }

    private String getUserId(WebSocketSession webSocketSession) {
        List<String> userId = webSocketSession.getHandshakeHeaders().get("UserId");
        return userId.get(0);
    }

    public WebSocketSession get(String id) {
        return map.get(id);
    }
}
