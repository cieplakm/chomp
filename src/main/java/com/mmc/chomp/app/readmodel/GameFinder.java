package com.mmc.chomp.app.readmodel;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameProjection;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameFinder {
    private GameRepository gameRepository;

    @Autowired
    public GameFinder(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameDto gameProjection(String gameId){
        Game game = gameRepository.get(AggregateId.create(gameId));
        GameProjection snapshot = game.snapshot();
        return new GameDto(
                snapshot.getBoard().getChocolateValue(),
                snapshot.getStatus(),
                snapshot.getWinnerId().getId(),
                snapshot.getCreatorId().getId(),
                snapshot.getJoinerId().getId()
        );
    }

}
