package com.mmc.chomp.app.canonicalmodel.publishedlanguage;

import com.mmc.chomp.app.system.infrastructure.event.impl.DefaultDomainEventPublisher;
import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;

public abstract class BaseAgregateRoot {
    protected AggregateId aggregateId;
    protected DefaultDomainEventPublisher domainEventPublisher;

    public AggregateId getAggregateId() {
        return aggregateId;
    }


}
