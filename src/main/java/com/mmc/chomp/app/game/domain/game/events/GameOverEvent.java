package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.game.domain.AggregateId;
import lombok.Value;

@Value
public class GameOverEvent extends Event {
    private AggregateId gameId;
    private AggregateId playerOne;
    private AggregateId playerTwo;
    private boolean isPlayerOneWinner;
    private boolean isPlayerTwoWinner;
}
