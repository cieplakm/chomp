package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.domain.game.GameProjection;
import lombok.Value;

@Value
public class TurnChangedEvent extends Event {
    private final AggregateId gameId;
    private final AggregateId playerOneId;
    private final AggregateId playerTwoId;
    private boolean playerOneTurn;
    private boolean playerTwoTurn;
    private GameProjection gameProjection;
}
