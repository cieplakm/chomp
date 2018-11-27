package com.mmc.chomp;

import com.mmc.chomp.ddd.annotation.domain.support.DomainEventPublisher;
import com.mmc.chomp.game.board.application.persistence.GameRepository;

public class IoC {
    public static DomainEventPublisher domainEventPublisher = new DomainEventPublisher();
    public static GameRepository gameRepository = new GameRepository();

    public static DomainEventPublisher domainEventPublisher(){
        return domainEventPublisher;
    }

    public static GameRepository gameRepository(){
        return gameRepository;
    }
}
