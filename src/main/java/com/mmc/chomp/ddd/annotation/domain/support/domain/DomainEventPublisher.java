package com.mmc.chomp.ddd.annotation.domain.support.domain;

import com.mmc.chomp.app.canonicalmodel.events.Event;

public interface DomainEventPublisher {
    void event(Event event);
}
