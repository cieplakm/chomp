package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.PlayerLeftEvent;
import com.mmc.chomp.app.response.PlayerLeftResponse;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener
public class PlayerLeftListenerListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    @EventSubscriber
    public void handle(PlayerLeftEvent event) {
        webSocketMessageSender.send(event.getStayer().getId(),
                new PlayerLeftResponse(event.getGameId().getId(),
                        event.getStayer().getId(),
                        event.getLeaver().getId()));
    }
}
