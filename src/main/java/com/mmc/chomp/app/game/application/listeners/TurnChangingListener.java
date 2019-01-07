package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.FakePlayer;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameProjection;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.game.domain.game.events.TurnChangedEvent;
import com.mmc.chomp.app.response.GameState;
import com.mmc.chomp.app.response.MoveResponse;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Timer;
import java.util.TimerTask;

@EventListener
public class TurnChangingListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    @Autowired
    GameRepository gameRepository;

    @EventSubscriber
    public void handle(TurnChangedEvent event) {
        GameProjection gp = event.getGameProjection();

        if (gp.isFakeOpponent() && gp.isCurrentPlayerTwo()) {

            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    Game game = gameRepository.get(gp.getGameId());
                    FakePlayer fakePlayer = new FakePlayer();
                    fakePlayer.move(game);
                }
            };

            Timer timer = new Timer();
            timer.schedule(tt, 1000L);
        }

        GameState gameState = new GameState(gp.getBoard().getChocolateValue(), gp.getBoard().getRows(), gp.getBoard().getCols(), gp.getPlayerOne().getId(), gp.getPlayerTwo().getId(), gp.getStatus());

        webSocketMessageSender.send(event.getPlayerOneId().getId(), new MoveResponse(gp.getGameId().getId(), gameState, event.isPlayerOneTurn()));
        webSocketMessageSender.send(event.getPlayerTwoId().getId(), new MoveResponse(gp.getGameId().getId(), gameState, event.isPlayerTwoTurn()));

    }
}
