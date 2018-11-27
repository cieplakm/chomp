package com.mmc.chomp.game.board.application.impl;

import com.mmc.chomp.IoC;
import com.mmc.chomp.ddd.annotation.domain.support.DomainEventPublisher;
import com.mmc.chomp.ddd.annotation.domain.support.PoisonLeftEvent;
import com.mmc.chomp.game.Participant;
import com.mmc.chomp.game.board.domain.ChompBoard;
import com.mmc.chomp.game.sharedkernel.Position;
import com.mmc.chomp.game.sharedkernel.exceptions.JoinException;
import com.mmc.chomp.game.turn.TurnChanger;

public class Game {
    private EmbeddedId embeddedId;
    private Participant currentTurn;
    private Participant creator;
    private ChompBoard chompBoard;
    private Participant joiner;

    private DomainEventPublisher domainEventPublisher;

    public Game(EmbeddedId embeddedId, Participant creator, ChompBoard chompBoard) {
        this.embeddedId = embeddedId;
        this.creator = creator;
        this.chompBoard = chompBoard;
        domainEventPublisher = IoC.domainEventPublisher();
    }

    public void join(Participant joiner){
        if (isFull()){
            throw new JoinException();
        }
        this.joiner = joiner;

        TurnChanger turnChanger = new TurnChanger();

        currentTurn = turnChanger.choose(creator, joiner);

    }

    public void move(Position position) {
        if (isFull()){
            chompBoard.peakChocolate(position);
        }

        if (chompBoard.checkIfPoisionLeft()) {
            domainEventPublisher.event(new PoisonLeftEvent(embeddedId));
        }

        changeTurn();
    }

    private void changeTurn() {
        TurnChanger turnChanger = new TurnChanger();
        currentTurn = turnChanger.switchTurn(currentTurn, creator, joiner);
    }

    public void left(Participant participant){
        if (participant.equals(creator)) {
            creator = null;
        }else {
            joiner = null;
        }
    }

    private boolean isFull() {
        return joiner != null;
    }

    public EmbeddedId getEmbeddedId() {
        return embeddedId;
    }

    public Participant currentTurn() {
        return currentTurn;
    }
}
