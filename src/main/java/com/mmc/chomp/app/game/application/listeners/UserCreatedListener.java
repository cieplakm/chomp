package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.canonicalmodel.events.UserCreatedEvent;
import com.mmc.chomp.app.game.application.api.service.RankingService;

public class UserCreatedListener implements EventHandler<UserCreatedEvent> {
    private RankingService rankingService;

    public UserCreatedListener(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    public void handle(UserCreatedEvent event){
        rankingService.create(event.getPlayerData());
    }
}
