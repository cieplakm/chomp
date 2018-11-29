package com.mmc.chomp.app.canonicalmodel.events;

import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;
import com.mmc.chomp.ddd.annotation.domain.support.ParticipantData;
import lombok.Value;

@Value
public class GameOver extends Event {
    private ParticipantData participantData;
    public GameOver(AggregateId gameId, ParticipantData winner) {
        this.participantData = winner;
        this.aggregateId = gameId;
    }
}
