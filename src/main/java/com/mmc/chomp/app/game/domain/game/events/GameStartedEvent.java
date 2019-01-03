package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.game.domain.AggregateId;
import lombok.Value;

@Value
public class GameStartedEvent extends Event {
    private AggregateId gameId;
    private AggregateId player1;
    private AggregateId player2;
}
