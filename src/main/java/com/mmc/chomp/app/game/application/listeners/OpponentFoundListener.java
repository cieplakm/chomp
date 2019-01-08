package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameFactory;
import com.mmc.chomp.app.game.domain.game.GameProjection;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.game.domain.game.events.OpponentFoundEvent;
import com.mmc.chomp.app.response.GameStartedResponse;
import com.mmc.chomp.app.response.GameState;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener
public class OpponentFoundListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;
    @Autowired
    GameFactory gameFactory;
    @Autowired
    GameRepository gameRepository;

    @EventSubscriber
    public void handle(OpponentFoundEvent event) {
        Game game = gameFactory.create(event.getPlayerOne() ,event.getSize());
        game.join(event.getPlayerTwo());
        gameRepository.save(game);
        game.start();
    }
}
