package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.ddd.annotation.event.Event;
import lombok.Data;
import lombok.Value;

@Event
@Value
public class UserLeftEvent extends com.mmc.chomp.app.game.domain.game.events.Event {

    private AggregateId leaver;
    private AggregateId gameId;

}
