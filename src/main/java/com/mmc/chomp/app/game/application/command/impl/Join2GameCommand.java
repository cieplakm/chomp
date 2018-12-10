package com.mmc.chomp.app.game.application.command.impl;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.application.command.Command;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.game.domain.user.User;
import com.mmc.chomp.app.game.domain.user.UserRepository;
import com.mmc.chomp.app.sharedkernel.Player;
import org.springframework.beans.factory.annotation.Autowired;

public class Join2GameCommand extends Command {
    private String gameId;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;


    @Override
    public void execute() {
        Game game = gameRepository.get(AggregateId.create(gameId));
        game.join(getPlayer(getUserId()));
    }

    private Player getPlayer(String userId) {
        User user = userRepository.get(AggregateId.create(userId));
        return new Player(user.getAggregateId(), user.getLogin());//TODO implement getting playerId
    }
}
