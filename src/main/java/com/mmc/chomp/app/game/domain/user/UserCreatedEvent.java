package com.mmc.chomp.app.game.domain.user;

import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.game.events.Event;
import lombok.Value;

@Value
public class UserCreatedEvent extends Event {
    private AggregateId playerId;
}
