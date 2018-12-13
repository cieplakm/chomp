package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.GameCreated;
import com.mmc.chomp.app.web.WebSocketSessionKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class GameCreatedEventListener implements EventHandler<GameCreated> {

    @Autowired
    private WebSocketSessionKeeper webSocketSessionKeeper;
    @Override
    public void handle(GameCreated event) {
        WebSocketSession webSocketSession = webSocketSessionKeeper.get(event.getCreator().getId());
        try {
            webSocketSession.sendMessage(new TextMessage("CREATED"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
