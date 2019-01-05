package com.mmc.chomp.app.game.application.command.impl;

import com.mmc.chomp.app.game.application.command.Command;
import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameFactory;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class CreateGameCommand extends Command {
    private int rows;
    private int cols;

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameFactory gameFactory;

    @Override
    public void execute() {
        Game game = gameFactory.create(AggregateId.create(getUserId()), new Size(rows, cols));
        gameRepository.save(game);
    }
}
