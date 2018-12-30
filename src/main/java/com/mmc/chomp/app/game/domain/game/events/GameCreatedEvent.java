package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.domain.game.GameProjection;
import lombok.Data;

@Data
public class GameCreatedEvent extends Event {
    private final AggregateId gameId;
    private final AggregateId creatorId;
    private final GameProjection gameProjection;

    public GameCreatedEvent(AggregateId gameId, AggregateId creatorId, GameProjection gameProjection) {
        super();
        this.gameId = gameId;
        this.creatorId = creatorId;
        this.gameProjection = gameProjection;
    }
}
