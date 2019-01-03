package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.GameProjection;
import com.mmc.chomp.app.game.domain.game.events.TurnChangedEvent;
import com.mmc.chomp.app.response.GameState;
import com.mmc.chomp.app.response.MoveResponse;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener
public class TurnChangingListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    @EventSubscriber
    public void handle(TurnChangedEvent event) {
        GameProjection gp = event.getGameProjection();

        GameState gameState = new GameState(gp.getBoard().getChocolateValue(), gp.getBoard().getRows(), gp.getBoard().getCols(), gp.getCreatorId().getId(), gp.getJoinerId().getId(), gp.getStatus());

        webSocketMessageSender.send(event.getPlayerOneId().getId(), new MoveResponse(gp.getGameId().getId(), gameState, event.isPlayerOneTurn()));

        webSocketMessageSender.send(event.getPlayerTwoId().getId(), new MoveResponse(gp.getGameId().getId(), gameState, event.isPlayerTwoTurn()));
    }
}
