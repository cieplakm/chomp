package com.mmc.chomp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmc.chomp.communication.ResponserProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;

@Slf4j
@Component
public class DefaultWebSocketHandler implements WebSocketHandler {

    private ResponserProcessor responserProcessor;

    private ObjectMapper objectMapper;


    @Autowired
    public DefaultWebSocketHandler(ResponserProcessor responserProcessor, ObjectMapper objectMapper) {
        this.responserProcessor = responserProcessor;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

        String response = responserProcessor.response((Serializable) webSocketMessage.getPayload());

        webSocketSession.sendMessage(new TextMessage(response));
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
