package com.mmc.chomp.app.game.infrastructure.repo.impl;

import com.mmc.chomp.app.game.domain.user.User;
import com.mmc.chomp.app.game.domain.user.UserRepository;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.ddd.support.repository.GenericAggregateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MockUserRepository implements UserRepository {
    public static final AggregateId MOCK_USER_ID_1 = AggregateId.create("2b9d9694-6513-43d3-84d7-bbed57b82df1");
    public static final AggregateId MOCK_USER_ID_2 = AggregateId.create("36e68452-12e8-4c3e-99b5-a046c0fba42b");

    private GenericAggregateRepository<User> genericAggregateRepository = new GenericAggregateRepository<>();

    @Autowired
    public MockUserRepository() {
        genericAggregateRepository.save(MOCK_USER_ID_1, new User(MOCK_USER_ID_1, "Ann", "pass1", System.currentTimeMillis()));
        genericAggregateRepository.save(MOCK_USER_ID_2, new User(MOCK_USER_ID_2, "Jon", "pass2", System.currentTimeMillis()));
        log.info("User added to repo id: {}", MOCK_USER_ID_1.getId());
        log.info("User added to repo id: {}", MOCK_USER_ID_2.getId());

    }

    @Override
    public void save(User user) {
        genericAggregateRepository.save(user.getAggregateId(), user);
    }

    @Override
    public User get(AggregateId id) {
        log.info("Getting user with Id {}", id.getId());
        return genericAggregateRepository.get(id);
    }

}
