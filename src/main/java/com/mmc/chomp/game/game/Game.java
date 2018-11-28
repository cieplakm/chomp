package com.mmc.chomp.game.game;

import com.mmc.chomp.IoC;
import com.mmc.chomp.ddd.annotation.domain.support.EmbeddedId;
import com.mmc.chomp.ddd.annotation.domain.support.GameOver;
import com.mmc.chomp.ddd.annotation.domain.support.ParticipantData;
import com.mmc.chomp.game.Participant;
import com.mmc.chomp.game.board.domain.ChompBoard;
import com.mmc.chomp.game.canonicalmodel.publishedlanguage.BaseAgregateRoot;
import com.mmc.chomp.game.sharedkernel.Position;
import com.mmc.chomp.game.sharedkernel.exceptions.ChocolateTakenException;
import com.mmc.chomp.game.sharedkernel.exceptions.JoinException;
import com.mmc.chomp.game.sharedkernel.exceptions.NoOponentException;
import com.mmc.chomp.game.sharedkernel.exceptions.NotStartedException;
import com.mmc.chomp.game.turn.TurnChanger;

import static com.mmc.chomp.game.game.Game.GameStatus.CREATED;
import static com.mmc.chomp.game.game.Game.GameStatus.FINISHED;
import static com.mmc.chomp.game.game.Game.GameStatus.STARTED;

public class Game extends BaseAgregateRoot {
    private GameStatus status;
    private ChompBoard chompBoard;
    private Participant currentTurn;
    private Participant creator;
    private Participant joiner;

    public Game(EmbeddedId embeddedId, Participant creator, ChompBoard chompBoard) {
        this.embeddedId = embeddedId;
        this.creator = creator;
        this.chompBoard = chompBoard;
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
    }

    public void start() {
        if (!isFull()){
            throw new NoOponentException();
        }
        choseWhoFirst();
        status = STARTED;
    }

    private void choseWhoFirst() {
        TurnChanger turnChanger = new TurnChanger();
        currentTurn = turnChanger.choose(creator, joiner);
    }

    public void move(Position position) {
        if (!STARTED.equals(status)) {
            throw new NotStartedException();
        }
        if (isFull()) {
            try {
                chompBoard.peakChocolate(position);
            } catch (ChocolateTakenException e) {
                e.printStackTrace();
            }
        }

        if (chompBoard.checkIfPoisionLeft()) {
            domainEventPublisher.event(new GameOver(embeddedId, new ParticipantData(wonParticipant().getLogin())));
            finishGame();
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
    }

    private void changeTurn() {
        TurnChanger turnChanger = new TurnChanger();
        currentTurn = turnChanger.switchTurn(currentTurn, creator, joiner);
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

    public Participant currentTurn() {
        return currentTurn;
    }



}
