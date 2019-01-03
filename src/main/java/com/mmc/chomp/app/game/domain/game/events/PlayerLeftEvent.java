package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.ddd.annotation.event.Event;
import lombok.Value;

@Event
@Value
public class PlayerLeftEvent extends com.mmc.chomp.app.game.domain.game.events.Event {

    private AggregateId leaver;
    private AggregateId stayer;
    private AggregateId gameId;

}
