package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.UserJoinedEvent;
import com.mmc.chomp.app.response.GameState;
import com.mmc.chomp.app.response.PlayerJoinedResponse;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener
public class NewJoinerListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    @EventSubscriber
    public void handle(UserJoinedEvent event) {
        com.mmc.chomp.app.game.domain.game.GameProjection gameProjection = event.getGameProjection();

        GameState gameState = new GameState(
                gameProjection.getBoard().getChocolateValue(),
                gameProjection.getBoard().getRows(),
                gameProjection.getBoard().getCols(),
                gameProjection.getCreatorId().getId(),
                gameProjection.getJoinerId().getId(),
                gameProjection.getStatus()
        );

        webSocketMessageSender.send(gameProjection.getCreatorId().getId(),
                new PlayerJoinedResponse(event.getGameProjection().getGameId().getId(), gameState));
    }
}
