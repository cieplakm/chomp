package com.mmc.chomp.game.board.application.impl;

import java.util.Objects;
import java.util.UUID;

public class EmbeddedId {
    private String id;

    public EmbeddedId(String id) {
        this.id = id;
    }

    public static EmbeddedId generate() {
        return new EmbeddedId(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EmbeddedId that = (EmbeddedId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
