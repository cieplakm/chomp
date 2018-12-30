package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.ddd.annotation.event.Event;
import lombok.Value;

@Event
@Value
public class UserJoinedEvent extends com.mmc.chomp.app.game.domain.game.events.Event {

    private AggregateId joiner;
    private AggregateId gameId;
    private AggregateId creator;



}
