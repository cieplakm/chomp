package com.mmc.chomp;

import com.mmc.chomp.app.game.domain.board.ChocolateBoxValue;
import lombok.Value;

@Value
public class GameProjection {
    private String status;
    private ChocolateBoxValue board;
    private String creatorId;
    private String joinerId;
    private String winnerId;
}
