package com.mmc.chomp.ddd.annotation.domain.support.domain;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.system.infrastructure.event.impl.DefaultDomainEventPublisher;

public abstract class BaseAgregateRoot {
    protected AggregateId aggregateId;
    protected DefaultDomainEventPublisher domainEventPublisher;

    public AggregateId getAggregateId() {
        return aggregateId;
    }


}
