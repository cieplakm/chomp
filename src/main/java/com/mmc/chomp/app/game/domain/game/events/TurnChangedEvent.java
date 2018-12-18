package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import lombok.Value;

@Value
public class TurnChangedEvent extends Event {
    private final AggregateId currentTurnPlayerId;
    private final AggregateId gameId;
}
