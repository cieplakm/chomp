package com.mmc.chomp.app.canonicalmodel.events;

import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;
import lombok.Data;

@Data
public abstract class Event {
    protected AggregateId aggregateId;
    private String message;
}
