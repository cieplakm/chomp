package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.FakePlayer;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameProjection;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.game.domain.game.events.GameStartedEvent;
import com.mmc.chomp.app.game.domain.game.events.TurnChangedEvent;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Timer;
import java.util.TimerTask;

@EventListener
public class FakeGameController {

    @Autowired
    private GameRepository gameRepository;

    @EventSubscriber
    public void handle(TurnChangedEvent event) {
        fakeMove(event.getGameProjection());
    }

    @EventSubscriber
    public void handle(GameStartedEvent event) {
        fakeMove(event.getSnapshot());
    }

    private void fakeMove(GameProjection gp) {
        if (gp.isFakeOpponent() && gp.isCurrentPlayerTwo()) {
            Timer timer = new Timer();

            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    FakePlayer fakePlayer = new FakePlayer();
                    Game game = gameRepository.get(gp.getGameId());
                    fakePlayer.move(game);
                    gameRepository.save(game);
                }
            };

            timer.schedule(tt, 800L);
        }
    }

}
