package com.mmc.chomp.app.canonicalmodel.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import lombok.Value;

@Value
public class TurnChangedEvent extends Event {
    private final AggregateId gameId;
    private final AggregateId palyerId;
}
