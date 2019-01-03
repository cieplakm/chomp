package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.game.domain.AggregateId;
import lombok.Value;

@Value
public class GameOverEvent extends Event {
    private AggregateId winner;
    private AggregateId looser;
    private AggregateId gameId;
}
