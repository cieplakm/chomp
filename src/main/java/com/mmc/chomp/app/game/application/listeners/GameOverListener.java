package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.GameOver;
import com.mmc.chomp.app.game.application.api.service.RankingService;
import org.springframework.stereotype.Component;

@Component
public class GameOverListener implements EventHandler<GameOver> {

    private RankingService rankingService;

    public GameOverListener(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @Override
    public void handle(GameOver event) {
        rankingService.changeRanking(event.getWinner(), event.getLooser());
    }
}
