package com.mmc.chomp.app.game.application.impl;

import com.mmc.chomp.IoC;
import com.mmc.chomp.app.game.application.api.service.GameService;
import com.mmc.chomp.app.game.domain.board.Board;
import com.mmc.chomp.app.game.domain.game.GameRepository;

import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;
import com.mmc.chomp.app.sharedkernel.Participant;
import com.mmc.chomp.app.game.domain.board.ChompBoardRepository;

import com.mmc.chomp.app.game.domain.board.BoardFactory;

import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.sharedkernel.Position;
import com.mmc.chomp.app.game.domain.game.TurnChanger;

public class DefaultGameService implements GameService {
    private TurnChanger turnChanger;
    ChompBoardRepository boardRepository;

    private GameRepository gameRepository = IoC.gameRepository();

    @Override
    public AggregateId createGame(Participant creator, Size boardSize) {
        Board chompBoard = BoardFactory.create(boardSize);
       // boardRepository.save(chompBoardEntity);

        AggregateId id = AggregateId.generate();
        Game game = new Game(id, creator, chompBoard);

        gameRepository.save(game);

        return id;
    }

    @Override
    public void joinToGame(AggregateId aggregateId, Participant participant){
        Game game = gameRepository.get(aggregateId);
        game.join(participant);
    }

    @Override
    public void move(AggregateId aggregateId, Position position){
        Game game = gameRepository.get(aggregateId);
        game.move(position);
    }

    @Override
    public void start(AggregateId id) {
        Game game = gameRepository.get(id);
        game.start();
    }

}
