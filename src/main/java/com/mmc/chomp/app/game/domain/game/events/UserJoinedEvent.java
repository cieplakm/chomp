package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.ddd.annotation.event.Event;

@Event
public class UserJoinedEvent {

    private AggregateId joiner;
    private AggregateId gameId;

}
