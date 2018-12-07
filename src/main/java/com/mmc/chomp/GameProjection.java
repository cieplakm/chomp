package com.mmc.chomp;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.domain.board.ChocolateBoxValue;
import lombok.Value;

@Value
public class GameProjection {
    private String status;
    private ChocolateBoxValue board;
    private AggregateId creatorId;
    private AggregateId joinerId;
    private AggregateId winnerId;
}
