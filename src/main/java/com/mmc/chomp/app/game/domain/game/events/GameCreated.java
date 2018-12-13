package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import lombok.Data;

@Data
public class GameCreated extends Event {
    private final AggregateId aggregateId;

    private final AggregateId creator;

    public GameCreated(AggregateId aggregateId, AggregateId creator) {
        super();
        this.aggregateId = aggregateId;
        this.creator = creator;
    }
}
