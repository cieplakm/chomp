package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.domain.game.GameProjection;
import lombok.Value;

@Value
public class TurnChangedEvent extends Event {
    private final AggregateId currentTurnPlayerId;
    private final AggregateId waiterPlayerId;
    private final AggregateId gameId;
    private GameProjection gameProjection;
}
