package com.mmc.chomp.app.game.domain.game;

import com.mmc.chomp.app.game.application.readmodel.GameProjection;
import com.mmc.chomp.app.game.domain.game.events.Event;
import com.mmc.chomp.app.game.domain.game.events.GameCreatedEvent;
import com.mmc.chomp.app.game.domain.game.events.GameOverEvent;
import com.mmc.chomp.app.game.domain.game.events.GameStartedEvent;
import com.mmc.chomp.app.game.domain.game.events.TurnChangedEvent;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.domain.board.Board;
import com.mmc.chomp.app.game.domain.game.events.UserLeftEvent;
import com.mmc.chomp.app.sharedkernel.Player;
import com.mmc.chomp.app.sharedkernel.Position;
import com.mmc.chomp.app.sharedkernel.exceptions.ChocolateTakenException;
import com.mmc.chomp.app.sharedkernel.exceptions.JoinException;
import com.mmc.chomp.app.sharedkernel.exceptions.NoOponentException;
import com.mmc.chomp.app.sharedkernel.exceptions.NotStartedException;
import com.mmc.chomp.ddd.annotation.domain.AggregateRoot;
import com.mmc.chomp.ddd.support.domain.BaseAggregateRoot;
import lombok.extern.slf4j.Slf4j;

import static com.mmc.chomp.app.game.domain.game.Game.GameStatus.CREATED;
import static com.mmc.chomp.app.game.domain.game.Game.GameStatus.FINISHED;
import static com.mmc.chomp.app.game.domain.game.Game.GameStatus.STARTED;

@Slf4j
@AggregateRoot
public class Game extends BaseAggregateRoot {
    private GameStatus status;
    private Board board;
    private AggregateId currentTurn;
    private AggregateId creator;
    private AggregateId joiner;
    private AggregateId winner;

    public Game(AggregateId gameId, AggregateId creatorId, Board board) {
        this.aggregateId = gameId;
        this.creator = creatorId;
        this.board = board;
    }

    enum GameStatus {
        CREATED, STARTED, FINISHED
    }

    public void create(){
        status = CREATED;
        event(new GameCreatedEvent(aggregateId, creator));
        log.info("Game {} created", getAggregateId().getId());
    }

    public void join(Player joiner) {
        if (isFull()) {
            throw new JoinException();
        }
        this.joiner = joiner.getAggregateId();
        log.info("New joiner at {} game", aggregateId.getId());
    }

    public void start() {
        if (!isFull()) {
            throw new NoOponentException();
        }
        choseWhoFirst();
        status = STARTED;
        log.info("Game {} started", aggregateId.getId());
        event(new GameStartedEvent(aggregateId, creator, joiner));
    }

    private void choseWhoFirst() {
        currentTurn = TurnChanger.drawLotsPlayer(creator, joiner);
        log.info("First will be: {} at {}", currentTurn.getId(), aggregateId.getId());
    }

    public void move(Position position) {
        if (!STARTED.equals(status)) {
            throw new NotStartedException();
        }
        if (isFull()) {
            try {
                board.peakChocolate(position);
                log.info("{} moved at {} game", currentTurn.getId(), aggregateId.getId());
            } catch (ChocolateTakenException e) {
                e.printStackTrace();
                return;
            }
        }

        if (board.isPoissonLeft()) {
            finishGame();
            return;
        }
        changeTurn();
    }

    private void finishGame() {
        status = FINISHED;
        winner = opponent(currentTurn);
        log.info("Game {} finished", aggregateId.getId());
        event(new GameOverEvent(winner, opponent(winner)));
    }

    private void changeTurn() {
        currentTurn = TurnChanger.switchTurn(currentTurn, creator, joiner);
        domainEventPublisher.publish(new TurnChangedEvent(aggregateId, currentTurn));
        log.info("{}'s turn at {} game", currentTurn.getId(), aggregateId.getId());
    }

    public void leave(AggregateId player) {
        if (status !=FINISHED){
            status = FINISHED;
            winner = opponent(player);
        }

        event(new UserLeftEvent(player, aggregateId));
    }

    private AggregateId opponent(AggregateId participant) {
        if (participant.equals(creator)) {
            return joiner;
        } else {
            return creator;
        }
    }

    private boolean isFull() {
        return joiner != null;
    }

    public GameProjection snapshot() {
        return new GameProjection(
                status.toString(),
                board.snapshot(),
                creator,
                joiner,
                winner
        );
    }

    private void event(Event event) {
        domainEventPublisher.publish(event);
    }

}
