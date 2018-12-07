package com.mmc.chomp.ddd.support.domain;

import com.mmc.chomp.app.game.domain.game.events.Event;

public interface DomainEventPublisher {
    void event(Event event);
}
