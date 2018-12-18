package com.mmc.chomp.app.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class WebSocketMessageSender {

    private WebSocketSessionKeeper webSocketSessionKeeper;
    private ObjectMapper objectMapper;

    @Autowired
    public WebSocketMessageSender(WebSocketSessionKeeper webSocketSessionKeeper, ObjectMapper objectMapper) {
        this.webSocketSessionKeeper = webSocketSessionKeeper;
        this.objectMapper = objectMapper;
    }

    public void send(String reciverId, Object o){
        String msg = "{}";
        try {
            msg = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        WebSocketSession webSocketSession = webSocketSessionKeeper.get(reciverId);

        try {
            webSocketSession.sendMessage(new TextMessage(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
