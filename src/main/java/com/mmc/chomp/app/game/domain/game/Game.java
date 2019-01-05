package com.mmc.chomp.app.game.domain.game;

import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.board.Board;
import com.mmc.chomp.app.game.domain.board.Position;
import com.mmc.chomp.app.game.domain.exceptions.ChocolateTakenException;
import com.mmc.chomp.app.game.domain.exceptions.JoinException;
import com.mmc.chomp.app.game.domain.exceptions.NoOponentException;
import com.mmc.chomp.app.game.domain.exceptions.NotStartedException;
import com.mmc.chomp.app.game.domain.game.events.Event;
import com.mmc.chomp.app.game.domain.game.events.GameCreatedEvent;
import com.mmc.chomp.app.game.domain.game.events.GameOverEvent;
import com.mmc.chomp.app.game.domain.game.events.PlayerLeftEvent;
import com.mmc.chomp.app.game.domain.game.events.TurnChangedEvent;
import com.mmc.chomp.app.game.domain.game.events.UserJoinedEvent;
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
    private AggregateId fakeOpponent;

    private Board board;

    private AggregateId currentTurn;

    private AggregateId playerOne;

    private AggregateId playerTwo;

    private AggregateId winner;

    public Game(AggregateId gameId, AggregateId creatorId, Board board) {
        this.aggregateId = gameId;
        this.playerOne = creatorId;
        this.board = board;
    }

    enum GameStatus {
        CREATED, STARTED, FINISHED
    }

    public void create() {
        status = CREATED;
        log.info("Game {} created", getAggregateId().getId());
    }

    public void join(AggregateId joiner) {
        if (isFull()) {
            throw new JoinException();
        }
        this.playerTwo = joiner;

        log.info("New playerTwo at {} game", aggregateId.getId());
    }

    public void joinFake(AggregateId joiner) {
        if (isFull()) {
            throw new JoinException();
        }
        this.playerTwo = joiner;
        fakeOpponent = playerTwo;

        log.info("New fake opponent at {} game", aggregateId.getId());
    }

    private boolean isFakeOpponent(){
        return fakeOpponent != null;
    }

    public void start() {
        if (!isFull() || status == FINISHED) {
            throw new NoOponentException();
        }
        choseWhoFirst();
        status = STARTED;

        //log.info("Game {} started", aggregateId.getId());
    }

    private void choseWhoFirst() {
        currentTurn = TurnChanger.drawLotsPlayer(playerOne, playerTwo);

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
        event(new GameOverEvent(aggregateId, playerOne, playerTwo, isWonOf(playerOne),  isWonOf(playerTwo)));

        log.info("Game {} finished", aggregateId.getId());
    }

    private boolean isWonOf(AggregateId player) {
        return player.equals(winner);
    }

    private void changeTurn() {
        currentTurn = TurnChanger.switchTurn(currentTurn, playerOne, playerTwo);
        TurnChangedEvent event = new TurnChangedEvent(aggregateId, playerOne, playerTwo, playerOne.equals(currentTurn), playerTwo.equals(currentTurn), snapshot());
        event(event);

        log.info("Is playerOne turn {}, is playerTwo turn {} at {} game", event.isPlayerOneTurn(), event.isPlayerTwoTurn(), aggregateId.getId());
    }

    public void leave(AggregateId leaver) {
        if (status != FINISHED) {
            status = FINISHED;
            winner = opponent(leaver);
        }
        event(new PlayerLeftEvent(leaver, opponent(leaver), aggregateId));
    }

    private AggregateId opponent(AggregateId participant) {
        if (participant.equals(playerOne)) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    private boolean isFull() {
        return playerTwo != null;
    }

    public GameProjection snapshot() {
        return new GameProjection(
                aggregateId,
                status.toString(),
                playerOne,
                playerTwo,
                winner,
                isTurnOf(playerOne),
                isTurnOf(playerTwo),
                board.snapshot(),
                isFakeOpponent()
        );
    }

    private boolean isTurnOf(AggregateId player) {
        return currentTurn.equals(player);
    }

    private void event(Event event) {
        domainEventPublisher.publish(event);
    }

}
