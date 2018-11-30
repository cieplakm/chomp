package com.mmc.chomp.app.system.infrastructure.event.impl;

import com.mmc.chomp.IoC;
import com.mmc.chomp.app.canonicalmodel.events.Event;
import com.mmc.chomp.app.canonicalmodel.events.GameOver;
import com.mmc.chomp.app.canonicalmodel.events.TurnChangedEvent;
import com.mmc.chomp.app.canonicalmodel.events.UserCreatedEvent;
import com.mmc.chomp.app.game.application.api.service.RankingService;
import com.mmc.chomp.app.game.application.impl.DefaultRankingService;
import com.mmc.chomp.app.game.application.listeners.EventHandler;
import com.mmc.chomp.app.game.application.listeners.GameOverListener;
import com.mmc.chomp.app.game.application.listeners.TurnChangingListener;
import com.mmc.chomp.app.game.application.listeners.UserCreatedListener;
import com.mmc.chomp.ddd.annotation.domain.support.domain.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultDomainEventPublisher implements DomainEventPublisher {

    private RankingService rankingService = new DefaultRankingService(IoC.getRankingRepository());
    private TurnChangingListener turnChangingListener = new TurnChangingListener();
    private UserCreatedListener userCreatedListener = new UserCreatedListener(rankingService);
    private EventHandler<GameOver> gameOverEventHandler = new GameOverListener(rankingService);

    @Override
    public void event(Event event) {
        if (event instanceof GameOver) {
            gameOverEventHandler.handle((GameOver) event);
        } else if (event instanceof TurnChangedEvent){
            turnChangingListener.handle((TurnChangedEvent) event);
        } else if (event instanceof UserCreatedEvent){
            userCreatedListener.handle((UserCreatedEvent) event);
        }
    }


}
