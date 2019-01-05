package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.game.domain.AggregateId;

import com.mmc.chomp.app.game.domain.board.Size;
import lombok.Value;

@Value
public class OpponentFoundEvent extends Event {
    private AggregateId playerOne;
    private AggregateId playerTwo;
    private Size size;
}
