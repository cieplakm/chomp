package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.FakePlayer;
import com.mmc.chomp.app.game.domain.board.Position;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameFactory;
import com.mmc.chomp.app.game.domain.game.GameProjection;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.game.domain.game.events.FakePlayerNeededEvent;
import com.mmc.chomp.app.game.domain.game.events.GameCreatedEvent;
import com.mmc.chomp.app.response.GameCreatedResponse;
import com.mmc.chomp.app.response.GameStartedResponse;
import com.mmc.chomp.app.response.GameState;
import com.mmc.chomp.app.response.MoveResponse;
import com.mmc.chomp.app.web.WebSocketMessageSender;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@EventListener
@Slf4j
public class OnFakePlayerNeededListener {

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    @Autowired
    GameFactory gameFactory;

    @Autowired
    GameRepository gameRepository;

    @EventSubscriber
    public void handle(FakePlayerNeededEvent event) {
        Game game = gameFactory.create(event.getUserId(), event.getSize());
        game.joinFake(AggregateId.generate());
        game.start();

        GameProjection snapshot = game.snapshot();



        if (snapshot.isFakeOpponent()) {
            if (snapshot.isCurrentPlayerTwo()) {
                FakePlayer fakePlayer = new FakePlayer();
                fakePlayer.move(game);

                snapshot = game.snapshot();
            }
        }

        gameRepository.save(game);

        GameState gameState = new GameState(snapshot.getBoard().getChocolateValue(),
                snapshot.getBoard().getRows(),
                snapshot.getBoard().getCols(),
                snapshot.getPlayerOne().getId(),
                snapshot.getPlayerTwo().getId(),
                snapshot.getStatus()
        );

        webSocketMessageSender.send(snapshot.getPlayerOne().getId(), new GameStartedResponse(snapshot.getGameId().getId(), snapshot.isCurrentPlayerOne(), gameState));

        log.info("FAKE PLAYER IS NEEDED");


    }
}
