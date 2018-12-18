package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.application.api.service.RankingService;
import com.mmc.chomp.app.game.domain.game.events.GameOverEvent;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;

@EventListener
public class GameOverListener {

    private RankingService rankingService;

    public GameOverListener(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @EventSubscriber
    public void handle(GameOverEvent event) {
        rankingService.changeRanking(event.getWinner(), event.getLooser());
    }
}
