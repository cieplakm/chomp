package com.mmc.chomp.app.game.domain.game;

import com.mmc.chomp.IoC;
import com.mmc.chomp.app.canonicalmodel.events.TurnChangedEvent;
import com.mmc.chomp.app.game.domain.board.Board;
import com.mmc.chomp.ddd.annotation.domain.AggregateRoot;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.canonicalmodel.events.GameOver;
import com.mmc.chomp.app.sharedkernel.Player;
import com.mmc.chomp.ddd.support.domain.BaseAgregateRoot;
import com.mmc.chomp.app.sharedkernel.Position;
import com.mmc.chomp.app.sharedkernel.exceptions.ChocolateTakenException;
import com.mmc.chomp.app.sharedkernel.exceptions.JoinException;
import com.mmc.chomp.app.sharedkernel.exceptions.NoOponentException;
import com.mmc.chomp.app.sharedkernel.exceptions.NotStartedException;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.PlayerData;
import lombok.extern.slf4j.Slf4j;

import static com.mmc.chomp.app.game.domain.game.Game.GameStatus.CREATED;
import static com.mmc.chomp.app.game.domain.game.Game.GameStatus.FINISHED;
import static com.mmc.chomp.app.game.domain.game.Game.GameStatus.STARTED;

@Slf4j
@AggregateRoot
public class Game extends BaseAgregateRoot {
    private GameStatus status;
    private Board board;
    private PlayerData currentTurn;
    private PlayerData creator;
    private PlayerData joiner;
    private PlayerData winner;

    public Game(AggregateId aggregateId, Player creator, Board board) {
        this.aggregateId = aggregateId;
        this.creator = creator.snapshot();
        this.board = board;
        domainEventPublisher = IoC.domainEventPublisher();

        status = CREATED;
    }

    enum GameStatus {
        CREATED, STARTED, FINISHED
    }

    public void join(Player joiner) {
        if (isFull()) {
            throw new JoinException();
        }
        this.joiner = joiner.snapshot();
        log.info("New joiner at {} game", aggregateId.getId());
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
        currentTurn = TurnChanger.drawLotsPlayer(creator, joiner);
        log.info("First will be: {} at {}", currentTurn.getLogin(), aggregateId.getId());
    }

    public void move(Position position) {
        if (!STARTED.equals(status)) {
            throw new NotStartedException();
        }
        if (isFull()) {
            try {
                board.peakChocolate(position);
                log.info("{} moved at {} game",  currentTurn.getLogin(), aggregateId.getId());
            } catch (ChocolateTakenException e) {
                e.printStackTrace();
            }
        }

        if (board.isPoissonLeft()) {
            finishGame();
            return;
        }
        changeTurn();
    }

    private void gameOverEvent() {
        domainEventPublisher.event(new GameOver(winner, opponent(winner)));
    }

    private void finishGame() {
        status = FINISHED;
        winner = opponent(currentTurn);
        log.info("Game {} finished", aggregateId.getId());
        gameOverEvent();
    }

    private void changeTurn() {
        currentTurn = TurnChanger.switchTurn(currentTurn, creator, joiner);
        domainEventPublisher.event(new TurnChangedEvent(aggregateId, currentTurn.getAggregateId()));
        log.info("{}'s turn at {} game", currentTurn.getLogin(), aggregateId.getId());
    }

    public void leave(Player player) {
        status = FINISHED;
        if (player.equals(creator)) {
            creator = null;
        } else {
            joiner = null;
        }
    }

    private PlayerData opponent(PlayerData participant){
        if (participant.equals(creator)) {
            return joiner;
        }else {
            return creator;
        }
    }

    public PlayerData getWinner(){
        return winner;
    }

    private boolean isFull() {
        return joiner != null;
    }

}
