package com.mmc.chomp;

import com.mmc.chomp.ddd.annotation.domain.support.DomainEventPublisher;
import com.mmc.chomp.game.board.application.persistence.GameRepository;

public class IoC {
    public static final DomainEventPublisher domainEventPublisher = new DomainEventPublisher();

    public static final GameRepository gameRepository = new GameRepository();
}
