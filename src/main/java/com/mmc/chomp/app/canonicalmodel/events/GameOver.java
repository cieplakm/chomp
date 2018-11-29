package com.mmc.chomp.app.canonicalmodel.events;

import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;
import com.mmc.chomp.ddd.annotation.domain.support.ParticipantData;
import lombok.Value;

@Value
public class GameOver extends Event {
    private ParticipantData participantData;
    public GameOver(AggregateId aggregateId, ParticipantData participantData) {
        this.participantData = participantData;
        this.aggregateId = aggregateId;
    }
}
