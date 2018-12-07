package com.mmc.chomp.ddd.support.domain;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;

public abstract class BaseAggregateRoot {
    protected AggregateId aggregateId;
    protected DomainEventPublisher domainEventPublisher;

    public AggregateId getAggregateId() {
        return aggregateId;
    }
}
