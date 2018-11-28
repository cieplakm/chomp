package com.mmc.chomp.game.canonicalmodel.publishedlanguage;

import com.mmc.chomp.ddd.annotation.domain.support.DomainEventPublisher;
import com.mmc.chomp.ddd.annotation.domain.support.EmbeddedId;

public abstract class BaseAgregateRoot {
    protected EmbeddedId embeddedId;
    protected DomainEventPublisher domainEventPublisher;

    public EmbeddedId getEmbeddedId() {
        return embeddedId;
    }


}
