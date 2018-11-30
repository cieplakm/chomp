package com.mmc.chomp.app.game.infrastructure.repo.impl;

import com.mmc.chomp.app.user.User;
import com.mmc.chomp.app.user.UserRepository;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.ddd.annotation.domain.support.infrastructure.repository.GenericAggregateRepository;

public class MockUserRepository implements UserRepository {
    private GenericAggregateRepository<User> genericAggregateRepository = new GenericAggregateRepository<>();

    @Override
    public void save(User user) {
        genericAggregateRepository.save(user.getAggregateId(), user);
    }

    @Override
    public User get(AggregateId id) {
        return genericAggregateRepository.get(id);
    }

}
