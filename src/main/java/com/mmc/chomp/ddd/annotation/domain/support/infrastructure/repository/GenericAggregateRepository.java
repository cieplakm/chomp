package com.mmc.chomp.ddd.annotation.domain.support.infrastructure.repository;

import com.mmc.chomp.ddd.annotation.domain.support.domain.BaseAgregateRoot;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;

import java.util.HashMap;
import java.util.Map;

public class GenericAggregateRepository<T extends BaseAgregateRoot> {

    private Map<AggregateId, T> map = new HashMap<>();

    public T get(AggregateId aggregateId) {
        return map.get(aggregateId);
    }

    public void save(AggregateId key, T aggregate) {
        map.put(key, aggregate);
    }
}
