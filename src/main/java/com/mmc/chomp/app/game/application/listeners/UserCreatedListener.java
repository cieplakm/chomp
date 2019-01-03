package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.application.api.service.RankingService;
import com.mmc.chomp.app.game.domain.user.UserCreatedEvent;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;

@EventListener
public class UserCreatedListener {
    private RankingService rankingService;

    public UserCreatedListener(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @EventSubscriber
    public void handle(UserCreatedEvent event) {
        rankingService.create(event.getPlayerId());
    }
}
