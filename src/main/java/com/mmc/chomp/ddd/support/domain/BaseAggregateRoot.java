package com.mmc.chomp.ddd.support.domain;

import com.mmc.chomp.app.game.domain.AggregateId;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseAggregateRoot {
    protected AggregateId aggregateId;
    @Autowired
    protected DomainEventPublisher domainEventPublisher;

    public AggregateId getAggregateId() {
        return aggregateId;
    }
}
