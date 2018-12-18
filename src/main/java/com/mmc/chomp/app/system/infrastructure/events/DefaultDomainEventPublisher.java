package com.mmc.chomp.app.system.infrastructure.events;

import com.mmc.chomp.app.game.domain.game.events.Event;
import com.mmc.chomp.app.system.infrastructure.events.impl.handlers.EventHandler;
import com.mmc.chomp.ddd.support.domain.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class DefaultDomainEventPublisher implements DomainEventPublisher {

    private Set<EventHandler> eventHandlers;

    @Autowired
    public DefaultDomainEventPublisher() {
        eventHandlers = new HashSet<>();
    }

    @Override
    public void registerHandler(EventHandler eventHandler){
        eventHandlers.add(eventHandler);
    }

    @Override
    public void publish(Event event) {
        for (EventHandler handler : eventHandlers){
            if (handler.canHandle(event)){
                handler.handle(event);
            }
        }
    }
}
