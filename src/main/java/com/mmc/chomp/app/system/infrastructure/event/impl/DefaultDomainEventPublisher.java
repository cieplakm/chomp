package com.mmc.chomp.app.system.infrastructure.event.impl;

import com.mmc.chomp.IoC;
import com.mmc.chomp.app.canonicalmodel.events.GameOver;
import com.mmc.chomp.app.game.domain.game.GameRepository;

import com.mmc.chomp.app.game.application.api.service.RankingService;
import com.mmc.chomp.app.game.application.impl.DefaultRankingService;
import com.mmc.chomp.ddd.annotation.domain.support.DomainEventPublisher;

import com.mmc.chomp.app.canonicalmodel.events.Event;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultDomainEventPublisher implements DomainEventPublisher {
    private GameRepository gameRepository;
    private RankingService rankingService;

    public DefaultDomainEventPublisher() {
        gameRepository = IoC.gameRepository();
        rankingService = new DefaultRankingService();
    }

    @Override
    public void event(Event event){
        if (event instanceof GameOver){
            GameOver gameOver = (GameOver) event;
            rankingService.changeRanking(gameOver.getAggregateId());
        }
    }
}
