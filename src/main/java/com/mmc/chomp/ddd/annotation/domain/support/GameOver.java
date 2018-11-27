package com.mmc.chomp.ddd.annotation.domain.support;

import lombok.Value;

@Value
public class GameOver extends Event {
    private ParticipantData participantData;
    public GameOver(EmbeddedId embeddedId, ParticipantData participantData) {
        this.participantData = participantData;
        this.embeddedId = embeddedId;
    }
}
