package com.mmc.chomp.game.board.application.impl;

import com.mmc.chomp.IoC;
import com.mmc.chomp.ddd.annotation.domain.support.EmbeddedId;
import com.mmc.chomp.game.Participant;
import com.mmc.chomp.game.board.application.persistence.ChompBoardRepository;
import com.mmc.chomp.game.board.application.persistence.GameRepository;
import com.mmc.chomp.game.board.application.service.XYZService;
import com.mmc.chomp.game.board.domain.BoardFactory;
import com.mmc.chomp.game.board.domain.ChompBoard;
import com.mmc.chomp.game.game.Game;
import com.mmc.chomp.game.board.domain.Size;
import com.mmc.chomp.game.sharedkernel.Position;
import com.mmc.chomp.game.turn.TurnChanger;

public class DefaultXYZService implements XYZService {
    private TurnChanger turnChanger;
    ChompBoardRepository boardRepository;

    private GameRepository gameRepository = IoC.gameRepository();

    @Override
    public EmbeddedId createGame(Participant creator, Size boardSize) {
        ChompBoard chompBoard = BoardFactory.create(boardSize);
       // boardRepository.save(chompBoardEntity);

        EmbeddedId id = EmbeddedId.generate();
        Game game = new Game(id, creator, chompBoard);

        gameRepository.save(game);

        return id;
    }

    @Override
    public void joinToGame(EmbeddedId embeddedId, Participant participant){
        Game game = gameRepository.get(embeddedId);
        game.join(participant);
    }

    @Override
    public void move(EmbeddedId embeddedId, Position position){
        Game game = gameRepository.get(embeddedId);
        game.move(position);
    }

    @Override
    public void start(EmbeddedId id) {
        Game game = gameRepository.get(id);
        game.start();
    }

}
