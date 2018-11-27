package com.mmc.chomp.ddd.annotation.domain.support;

import com.mmc.chomp.game.board.application.impl.EmbeddedId;
import lombok.Data;

@Data
public class Event {
    protected EmbeddedId embeddedId;
    private String message;
}
