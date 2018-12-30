package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.PlayerData;
import lombok.Value;

@Value
public class GameOverEvent extends Event {
    private AggregateId winner;
    private AggregateId looser;
    private AggregateId gameId;
}
