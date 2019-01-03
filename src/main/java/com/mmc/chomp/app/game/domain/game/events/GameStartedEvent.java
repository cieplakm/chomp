package com.mmc.chomp.app.game.domain.game.events;

import com.mmc.chomp.app.game.domain.game.GameProjection;
import lombok.Value;

@Value
public class GameStartedEvent extends Event {
    private GameProjection snapshot;
    private boolean isCreatorTurn;
    private boolean isJoinerTurn;
}
