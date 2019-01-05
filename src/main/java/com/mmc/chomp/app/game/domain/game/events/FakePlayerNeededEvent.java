package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.board.Size;
import lombok.Value;

@Value
public class FakePlayerNeededEvent extends Event {
    private final AggregateId userId;
    private final Size size;
}
