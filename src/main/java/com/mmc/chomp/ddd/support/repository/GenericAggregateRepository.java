package com.mmc.chomp.ddd.support.repository;

import com.mmc.chomp.ddd.support.domain.BaseAggregateRoot;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;

import java.util.HashMap;
import java.util.Map;

public class GenericAggregateRepository<T extends BaseAggregateRoot> {

    private Map<AggregateId, T> map = new HashMap<>();

    public T get(AggregateId aggregateId) {
        return map.get(aggregateId);
    }

    public void save(AggregateId key, T aggregate) {
        map.put(key, aggregate);
    }
}
