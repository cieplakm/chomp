package com.mmc.chomp.app.game.domain.game;

import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;

public interface GameRepository {
    void save(Game game);
    Game get(AggregateId aggregateId);

}
