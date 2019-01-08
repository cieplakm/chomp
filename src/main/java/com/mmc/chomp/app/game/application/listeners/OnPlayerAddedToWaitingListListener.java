package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.WaitingList;
import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameFactory;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.game.domain.game.events.PlayerAddedToWaitingListEvent;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@EventListener
@Slf4j
public class OnPlayerAddedToWaitingListListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    @Autowired
    private GameFactory gameFactory;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private WaitingList waitingList;

    @EventSubscriber
    public void handle(PlayerAddedToWaitingListEvent event) {
        TimerTask fakePlayerNeededEvent = new TimerTask() {
            @Override
            public void run() {
                boolean stillOnList = waitingList.isStillOnList(event.getUserId());

                if (stillOnList) {
                    log.info("User ({}) is still on waiting list. Creating fake player.", event.getUserId());
                    createGameWithFakePlayer(event.getUserId(), event.getSize());
                    waitingList.remove(event.getUserId());
                }
            }
        };

        Timer timer = new Timer();
        long delay = 1000L * (new Random().nextInt(1) + 2);
        timer.schedule(fakePlayerNeededEvent, delay);
    }

    private void createGameWithFakePlayer(AggregateId playerId, Size size) {
        Game game = gameFactory.create(playerId, size);
        game.joinFake(AggregateId.generate());
        gameRepository.save(game);
        game.start();
    }
}
