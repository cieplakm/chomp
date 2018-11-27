package com.mmc.chomp.game.board.application.impl;

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

    public Game(EmbeddedId embeddedId, Participant creator, ChompBoard chompBoard) {
        this.embeddedId = embeddedId;
        this.creator = creator;
        this.chompBoard = chompBoard;
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
