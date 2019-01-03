package com.mmc.chomp.app.game.domain.game;

import com.mmc.chomp.app.game.domain.AggregateId;

public interface GameRepository {
    void save(Game game);
    Game get(AggregateId aggregateId);

}
