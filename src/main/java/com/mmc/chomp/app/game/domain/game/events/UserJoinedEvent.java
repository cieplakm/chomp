package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.game.domain.game.GameProjection;
import com.mmc.chomp.ddd.annotation.event.Event;
import lombok.Value;

@Event
@Value
public class UserJoinedEvent extends com.mmc.chomp.app.game.domain.game.events.Event {

    private GameProjection gameProjection;

    public UserJoinedEvent(GameProjection snapshot) {

        gameProjection = snapshot;
    }
}
