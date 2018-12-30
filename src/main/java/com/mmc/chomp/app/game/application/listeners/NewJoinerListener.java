package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.UserJoinedEvent;
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
        webSocketMessageSender.send(event.getCreator().getId(),
                new PlayerJoinedResponse(event.getGameId().getId(), event.getJoiner().getId()));
    }
}
