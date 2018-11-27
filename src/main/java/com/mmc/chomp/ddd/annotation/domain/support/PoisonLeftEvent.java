package com.mmc.chomp.ddd.annotation.domain.support;

import com.mmc.chomp.game.board.application.impl.EmbeddedId;

public class PoisonLeftEvent extends Event {

    public PoisonLeftEvent(EmbeddedId embeddedId) {
        this.embeddedId = embeddedId;
    }
}
