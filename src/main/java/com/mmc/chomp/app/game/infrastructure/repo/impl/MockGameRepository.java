package com.mmc.chomp.app.game.infrastructure.repo.impl;

import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.game.domain.AggregateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MockGameRepository implements GameRepository {
    private GameRepository mock = new GameRepository();

    @Autowired
    public MockGameRepository() {

    }

    @Override
    public void save(Game game) {
        mock.save(game);
    }

    @Override
    public Game get(AggregateId aggregateId) {
        return mock.get(aggregateId);
    }

    public static class GameRepository {

        private Map<AggregateId, Game> map = new HashMap<>();

        private Game get(AggregateId aggregateId) {
            return map.get(aggregateId);
        }

        private void save(Game game) {
            map.put(game.getAggregateId(), game);
        }

    }
}
