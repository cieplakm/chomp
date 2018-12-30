package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.application.readmodel.response.CreateGameResponse;
import com.mmc.chomp.app.game.domain.game.events.GameCreatedEvent;
import com.mmc.chomp.app.game.domain.game.events.GameStartedEvent;
import com.mmc.chomp.app.response.GameStartedResponse;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener
public class StartedEventListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    @EventSubscriber
    public void handle(GameStartedEvent event) {
        webSocketMessageSender.send(event.getPlayer1().getId(), new GameStartedResponse(event.getGameId().getId()));
        webSocketMessageSender.send(event.getPlayer2().getId(), new GameStartedResponse(event.getGameId().getId()));
    }
}
