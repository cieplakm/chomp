package com.mmc.chomp.ddd.annotation.domain.support;

import com.mmc.chomp.game.board.application.impl.EmbeddedId;
import lombok.Data;

@Data
public class Event {
    private EmbeddedId embeddedId;
    private String message;
}
