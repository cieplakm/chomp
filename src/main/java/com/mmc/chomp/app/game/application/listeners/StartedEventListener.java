package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.GameStartedEvent;
import com.mmc.chomp.app.response.GameStartedResponse;
import com.mmc.chomp.app.response.GameState;
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
        GameState gameState = new GameState(event.getSnapshot().getBoard().getChocolateValue(),
                event.getSnapshot().getBoard().getRows(),
                event.getSnapshot().getBoard().getCols(),
                event.getSnapshot().getPlayerOne().getId(),
                event.getSnapshot().getPlayerTwo().getId(),
                event.getSnapshot().getStatus()
        );
        webSocketMessageSender.send(event.getSnapshot().getPlayerOne().getId(), new GameStartedResponse(event.getSnapshot().getGameId().getId(), event.isCreatorTurn(), gameState));
        webSocketMessageSender.send(event.getSnapshot().getPlayerTwo().getId(), new GameStartedResponse(event.getSnapshot().getGameId().getId(), event.isJoinerTurn(), gameState));
    }
}
