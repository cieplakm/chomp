package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.GameCreatedEvent;
import com.mmc.chomp.app.response.GameCreatedResponse;
import com.mmc.chomp.app.response.GameState;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener
public class GameCreatedEventListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    @EventSubscriber
    public void handle(GameCreatedEvent event) {

        GameState gameState = new GameState(event.getGameProjection().getBoard().getChocolateValue(),
                event.getGameProjection().getBoard().getRows(),
                event.getGameProjection().getBoard().getCols(),
                event.getCreatorId().getId(),
                null,
                event.getGameProjection().getStatus());

        webSocketMessageSender.send(event.getCreatorId().getId(), new GameCreatedResponse(event.getGameId().getId(), gameState));
    }
}
