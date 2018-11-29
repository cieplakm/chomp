package com.mmc.chomp.app.system.infrastructure.event.impl;

import com.mmc.chomp.IoC;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.game.infrastructure.repo.impl.MockGameRepository;
import com.mmc.chomp.ddd.annotation.domain.support.DomainEventPublisher;

import com.mmc.chomp.app.canonicalmodel.events.Event;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultDomainEventPublisher implements DomainEventPublisher {
    private GameRepository gameRepository;

    public DefaultDomainEventPublisher() {
        gameRepository = IoC.gameRepository();
    }

    @Override
    public void event(Event event){

    }
}
