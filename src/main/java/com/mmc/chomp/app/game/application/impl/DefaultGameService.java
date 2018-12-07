package com.mmc.chomp.app.game.application.impl;

import com.mmc.chomp.GameProjection;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.application.api.service.GameService;
import com.mmc.chomp.app.game.domain.board.Board;
import com.mmc.chomp.app.game.domain.board.BoardFactory;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.sharedkernel.Player;
import com.mmc.chomp.app.sharedkernel.Position;
import com.mmc.chomp.app.game.domain.user.User;
import com.mmc.chomp.app.game.domain.user.UserRepository;
import com.mmc.chomp.ddd.annotation.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

@ApplicationService
public class DefaultGameService implements GameService {
    private GameRepository gameRepository;
    private UserRepository userRepository;

    @Autowired
    public DefaultGameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AggregateId createGame(AggregateId creator, int rows, int cols) {
        Board chompBoard = BoardFactory.create(rows, cols);

        AggregateId id = AggregateId.generate();
        Game game = new Game(id, getPlayer(creator), chompBoard);

        gameRepository.save(game);

        return id;
    }

    @Override
    public void joinToGame(AggregateId userId, AggregateId gameId) {
        Game game = gameRepository.get(gameId);
        game.join(getPlayer(userId));
    }

    @Override
    public void move(AggregateId gameId, Position position) {
        Game game = gameRepository.get(gameId);
        game.move(position);
    }

    @Override
    public void start(AggregateId gameId) {
        Game game = gameRepository.get(gameId);
        game.start();
    }

    @Override
    public GameProjection projection(AggregateId gameId) {
        Game game = gameRepository.get(gameId);
        return game.snapshot();
    }

    private Player getPlayer(AggregateId userId) {
        User user = userRepository.get(userId);
        return new Player(userId, user.getLogin());//TODO implement getting playerId
    }


}
