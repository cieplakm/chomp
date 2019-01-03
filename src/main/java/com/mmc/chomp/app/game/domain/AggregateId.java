package com.mmc.chomp.app.game.domain;

import java.util.Objects;
import java.util.UUID;

public class AggregateId {
    private String id;

    private AggregateId(String id) {
        this.id = id;
    }

    public static AggregateId create(String userId) {
        return new AggregateId(userId);
    }

    public static AggregateId generate() {
        return new AggregateId(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AggregateId that = (AggregateId) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
