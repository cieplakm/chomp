package com.mmc.chomp.game;

import com.mmc.chomp.ddd.annotation.domain.support.EmbeddedId;
import com.mmc.chomp.ddd.annotation.domain.support.ParticipantData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Participant {
    private EmbeddedId embeddedId;
    private String login;
}
