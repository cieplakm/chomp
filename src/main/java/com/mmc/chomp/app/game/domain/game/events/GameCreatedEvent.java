package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import lombok.Data;

@Data
public class GameCreatedEvent extends Event {
    private final AggregateId gameId;
    private final AggregateId creatorId;

    public GameCreatedEvent(AggregateId gameId, AggregateId creatorId) {
        super();
        this.gameId = gameId;
        this.creatorId = creatorId;
    }
}
