package com.mmc.chomp.game.board.application.persistence;

import com.mmc.chomp.game.board.application.impl.EmbeddedId;
import com.mmc.chomp.game.board.application.impl.Game;

import java.util.HashMap;
import java.util.Map;


public class GameRepository {

    private Map<EmbeddedId, Game> map = new HashMap<>();

    public Game get(EmbeddedId embeddedId) {
        return map.get(embeddedId);
    }

    public void save(Game game) {
        map.put(game.getEmbeddedId(), game);
    }

}
