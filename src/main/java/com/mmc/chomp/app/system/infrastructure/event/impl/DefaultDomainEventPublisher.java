package com.mmc.chomp.app.system.infrastructure.event.impl;

import com.mmc.chomp.app.game.application.listeners.EventHandler;
import com.mmc.chomp.app.game.application.listeners.GameCreatedEventListener;
import com.mmc.chomp.app.game.application.listeners.TurnChangingListener;
import com.mmc.chomp.app.game.application.listeners.UserCreatedListener;
import com.mmc.chomp.app.game.domain.game.events.Event;
import com.mmc.chomp.app.game.domain.game.events.GameCreated;
import com.mmc.chomp.app.game.domain.game.events.GameOver;
import com.mmc.chomp.app.game.domain.game.events.TurnChangedEvent;
import com.mmc.chomp.app.game.domain.game.events.UserCreatedEvent;
import com.mmc.chomp.ddd.support.domain.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DefaultDomainEventPublisher implements DomainEventPublisher {
    private TurnChangingListener turnChangingListener;

    private UserCreatedListener userCreatedListener;

    private GameCreatedEventListener gameCreatedEventListener;

    private EventHandler<GameOver> gameOverEventHandler;

    private List<EventHandler> eventHandlers;

    @Autowired
    public DefaultDomainEventPublisher(TurnChangingListener turnChangingListener,
            UserCreatedListener userCreatedListener,
            GameCreatedEventListener gameCreatedEventListener, EventHandler<GameOver> gameOverEventHandler) {
        this.turnChangingListener = turnChangingListener;
        this.userCreatedListener = userCreatedListener;
        this.gameCreatedEventListener = gameCreatedEventListener;
        this.gameOverEventHandler = gameOverEventHandler;
    }

    @Override
    public void event(Event event) {
        if (event instanceof GameOver) {
            gameOverEventHandler.handle((GameOver) event);
        } else if (event instanceof TurnChangedEvent) {
            turnChangingListener.handle((TurnChangedEvent) event);
        } else if (event instanceof UserCreatedEvent) {
            userCreatedListener.handle((UserCreatedEvent) event);
        } else if (event instanceof GameCreated) {
            gameCreatedEventListener.handle((GameCreated) event);
        }
    }
}
