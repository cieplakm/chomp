package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.application.readmodel.response.CreateGameResponse;
import com.mmc.chomp.app.game.domain.game.events.GameCreatedEvent;
import com.mmc.chomp.app.response.GameCreatedResponse;
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
        webSocketMessageSender.send(event.getCreatorId().getId(), new GameCreatedResponse(event.getGameId().getId(),
                event.getGameProjection().getBoard().getRows(),
                event.getGameProjection().getBoard().getCols(),
                event.getGameProjection().getBoard().getChocolateValue()));
    }
}
