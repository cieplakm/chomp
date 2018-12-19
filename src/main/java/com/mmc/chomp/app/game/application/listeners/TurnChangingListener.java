package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.TurnChangedEvent;
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
        TurnDto dto = new TurnDto(event.getCurrentTurnPlayerId().getId(), event.getWaiterPlayerId().getId(), event.getGameId().getId());
        //inform both users
        webSocketMessageSender.send(event.getCurrentTurnPlayerId().getId(), dto);
        webSocketMessageSender.send(event.getWaiterPlayerId().getId(), dto);
    }
}
