package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.application.api.service.RankingService;
import com.mmc.chomp.app.game.domain.game.events.GameOverEvent;
import com.mmc.chomp.app.response.GameOverResponse;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener
public class GameOverListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    private RankingService rankingService;

    public GameOverListener(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @EventSubscriber
    public void handle(GameOverEvent event) {
        GameOverResponse response = new GameOverResponse(event.getGameId().getId(), event.getWinner().getId(), event.getLooser().getId());
        rankingService.changeRanking(event.getWinner(), event.getLooser());
        webSocketMessageSender.send(event.getWinner().getId(), response);
        webSocketMessageSender.send(event.getLooser().getId(), response);
    }
}
