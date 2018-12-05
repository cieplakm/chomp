package com.mmc.chomp.app.game.application.impl;

import com.mmc.chomp.app.game.application.api.service.GameService;
import com.mmc.chomp.app.game.domain.board.Board;
import com.mmc.chomp.app.game.domain.game.GameRepository;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.sharedkernel.Player;


import com.mmc.chomp.app.game.domain.board.BoardFactory;

import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.sharedkernel.Position;
import com.mmc.chomp.app.system.user.UserService;


public class DefaultGameService implements GameService {
    private GameRepository gameRepository;


    public DefaultGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public AggregateId createGame(Player creator, Size boardSize) {
        Board chompBoard = BoardFactory.create(boardSize);

        AggregateId id = AggregateId.generate();
        Game game = new Game(id, creator, chompBoard);

        gameRepository.save(game);

        return id;
    }

    @Override
    public void joinToGame(AggregateId aggregateId, Player player){
        Game game = gameRepository.get(aggregateId);
        game.join(player);
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
