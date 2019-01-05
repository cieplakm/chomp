package com.mmc.chomp.app.game.domain.game;

import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.board.Board;
import com.mmc.chomp.app.game.domain.board.BoardFactory;
import com.mmc.chomp.app.game.domain.board.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class GameFactory {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    public Game create(AggregateId userId, Size size) {
        Board board = BoardFactory.create(size);
        Game game = new Game(AggregateId.create("ffbc2602-3603-4137-ab3f-3a6d1525cc37"), userId, board);

        beanFactory.autowireBean(game);
        game.create();
        return game;
    }
}
