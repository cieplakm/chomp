package com.mmc.chomp.app.readmodel;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameProjection;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import org.springframework.stereotype.Component;

@Component
public class GameFinder {
    private GameRepository gameRepository;

    public GameProjection gameProjection(String gameId){
        Game game = gameRepository.get(AggregateId.create(gameId));
        return game.snapshot();
    }

}
