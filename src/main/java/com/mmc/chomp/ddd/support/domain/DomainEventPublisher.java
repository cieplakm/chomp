package com.mmc.chomp.ddd.support.domain;

import com.mmc.chomp.app.game.domain.game.events.Event;
import com.mmc.chomp.app.system.infrastructure.events.impl.handlers.EventHandler;

public interface DomainEventPublisher {
    void registerHandler(EventHandler eventHandler);

    void publish(Event event);
}
