package com.mmc.chomp.app.game.domain.game;

import com.mmc.chomp.IoC;
import com.mmc.chomp.app.game.domain.board.Board;
import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;
import com.mmc.chomp.app.canonicalmodel.events.GameOver;
import com.mmc.chomp.app.sharedkernel.Participant;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.BaseAgregateRoot;
import com.mmc.chomp.app.sharedkernel.Position;
import com.mmc.chomp.app.sharedkernel.exceptions.ChocolateTakenException;
import com.mmc.chomp.app.sharedkernel.exceptions.JoinException;
import com.mmc.chomp.app.sharedkernel.exceptions.NoOponentException;
import com.mmc.chomp.app.sharedkernel.exceptions.NotStartedException;

import lombok.extern.slf4j.Slf4j;

import static com.mmc.chomp.app.game.domain.game.Game.GameStatus.CREATED;
import static com.mmc.chomp.app.game.domain.game.Game.GameStatus.FINISHED;
import static com.mmc.chomp.app.game.domain.game.Game.GameStatus.STARTED;

@Slf4j
public class Game extends BaseAgregateRoot {
    private GameStatus status;
    private Board board;
    private Participant currentTurn;
    private Participant creator;
    private Participant joiner;

    public Game(AggregateId aggregateId, Participant creator, Board board) {
        this.aggregateId = aggregateId;
        this.creator = creator;
        this.board = board;
        domainEventPublisher = IoC.domainEventPublisher();

        status = CREATED;
    }

    enum GameStatus {
        CREATED, STARTED, FINISHED
    }

    public void join(Participant joiner) {
        if (isFull()) {
            throw new JoinException();
        }
        this.joiner = joiner;
        log.info("New joiner at {} app", aggregateId.getId());
    }

    public void start() {
        if (!isFull()){
            throw new NoOponentException();
        }
        choseWhoFirst();
        status = STARTED;
        log.info("Game {} started", aggregateId.getId());
    }

    private void choseWhoFirst() {
        TurnChanger turnChanger = new TurnChanger();
        currentTurn = turnChanger.choose(creator, joiner);
        log.info("First will be: {} at {}", currentTurn.snapshot().getLogin(), aggregateId.getId());
    }

    public void move(Position position) {
        if (!STARTED.equals(status)) {
            throw new NotStartedException();
        }
        if (isFull()) {
            try {
                board.peakChocolate(position);
                log.info("{} moved at {} app",  currentTurn.snapshot().getLogin(), aggregateId.getId());
            } catch (ChocolateTakenException e) {
                e.printStackTrace();
            }
        }

        if (board.checkIfPoisionLeft()) {
            finishGame();
            domainEventPublisher.event(new GameOver(aggregateId, wonParticipant().snapshot()));
            return;
        }
        changeTurn();
    }

    private Participant wonParticipant() {
        if (currentTurn.equals(creator)) {
            return joiner;
        }else {
            return creator;
        }
    }

    private void finishGame() {
        status = FINISHED;
        log.info("Game {} finished", aggregateId.getId());
    }

    private void changeTurn() {
        TurnChanger turnChanger = new TurnChanger();
        currentTurn = turnChanger.switchTurn(currentTurn, creator, joiner);
        log.info("{}'s turn at {} app", currentTurn.snapshot().getLogin(), aggregateId.getId());
    }

    public void leave(Participant participant) {
        status = FINISHED;
        if (participant.equals(creator)) {
            creator = null;
        } else {
            joiner = null;
        }
    }

    private boolean isFull() {
        return joiner != null;
    }

}
