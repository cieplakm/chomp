package com.mmc.chomp.app.canonicalmodel.publishedlanguage;

import java.util.Objects;
import java.util.UUID;

public class AggregateId {
    private String id;

    public AggregateId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static AggregateId generate() {
        return new AggregateId(UUID.randomUUID().toString());
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
