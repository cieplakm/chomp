package com.mmc.chomp.app.game.domain.game;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.domain.board.Board;
import com.mmc.chomp.app.game.domain.board.BoardFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class GameFactory {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    public Game create(String userId, int rows, int cols) {
        Board board = BoardFactory.create(rows, cols);
        Game game = new Game(AggregateId.generate(), AggregateId.create(userId), board);

        beanFactory.autowireBean(game);
        game.create();
        return game;
    }
}
