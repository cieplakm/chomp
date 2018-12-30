package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.TurnChangedEvent;
import com.mmc.chomp.app.response.MoveResponse;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.app.web.dto.TurnDto;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener
public class TurnChangingListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    @EventSubscriber
    public void handle(TurnChangedEvent event){
        //inform both users
        webSocketMessageSender.send(event.getCurrentTurnPlayerId().getId(), new MoveResponse(event.getGameId().getId(), event.getGameProjection().getBoard().getChocolateValue()));
        webSocketMessageSender.send(event.getWaiterPlayerId().getId(), new MoveResponse(event.getGameId().getId(), event.getGameProjection().getBoard().getChocolateValue()));

    }
}
