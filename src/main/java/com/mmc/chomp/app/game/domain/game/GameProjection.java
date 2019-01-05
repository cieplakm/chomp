package com.mmc.chomp.app.game.domain.game;

import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.board.ChocolateBoxValue;
import lombok.Value;

@Value
public class GameProjection {
    private AggregateId gameId;
    private String status;
    private AggregateId playerOne;
    private AggregateId playerTwo;
    private AggregateId winnerId;
    private boolean isCurrentPlayerOne;
    private boolean isCurrentPlayerTwo;
    private ChocolateBoxValue board;

}
