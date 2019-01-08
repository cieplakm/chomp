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
        webSocketMessageSender.send(event.getPlayerOne().getId(), new GameOverResponse(event.getGameId().getId(), event.isPlayerOneWinner()));
        webSocketMessageSender.send(event.getPlayerTwo().getId(), new GameOverResponse(event.getGameId().getId(), event.isPlayerTwoWinner()));
        rankingService.changeRanking(event.getPlayerOne(), event.getPlayerTwo());
    }
}
