package com.mmc.chomp.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.mmc.chomp.app.game.application.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@Component
public class DefaultWebSocketHandler implements WebSocketHandler {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    private ObjectMapper objectMapper;

    private WebSocketSessionKeeper webSocketSessionKeeper;


    @Autowired
    public DefaultWebSocketHandler(ObjectMapper objectMapper, WebSocketSessionKeeper webSocketSessionKeeper) {
        this.objectMapper = objectMapper;
        this.webSocketSessionKeeper = webSocketSessionKeeper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        webSocketSessionKeeper.add(webSocketSession);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

        Command command = objectMapper.readValue((String) webSocketMessage.getPayload(), Command.class);
        beanFactory.autowireBean(command);
        command.execute();

//        String response = responserProcessor.response((Serializable) webSocketMessage.getPayload());
//
//        webSocketSession.sendMessage(new TextMessage(response));
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        webSocketSessionKeeper.remove(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
