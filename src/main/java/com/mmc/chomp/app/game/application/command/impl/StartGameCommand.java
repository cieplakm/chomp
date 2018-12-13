package com.mmc.chomp.app.game.application.command.impl;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.application.command.Command;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class StartGameCommand extends Command {
    private String gameId;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void execute() {
        Game game = gameRepository.get(AggregateId.create(gameId));
        game.start();
    }
}
