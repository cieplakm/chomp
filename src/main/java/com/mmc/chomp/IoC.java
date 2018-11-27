package com.mmc.chomp;

import com.mmc.chomp.ddd.annotation.domain.support.DomainEventPublisher;
import com.mmc.chomp.game.board.application.persistence.GameRepository;

public class IoC {
    public static DomainEventPublisher domainEventPublisher ;
    public static GameRepository gameRepository;

    public static DomainEventPublisher domainEventPublisher(){
        if (domainEventPublisher == null) domainEventPublisher = new DomainEventPublisher();
        return domainEventPublisher;
    }

    public static GameRepository gameRepository(){
        if (gameRepository == null){
            gameRepository= new GameRepository();
        }
        return gameRepository;
    }
}
