package com.mmc.chomp.app.game.domain.user;

import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.ddd.support.domain.DomainEventPublisher;

public class DefaultUserService implements UserService{
    private UserRepository userRepository;
    private DomainEventPublisher domainEventPublisher;

    public DefaultUserService(UserRepository userRepository, DomainEventPublisher domainEventPublisher) {
        this.userRepository = userRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public AggregateId create(String login, String pass){
        User user = new User(AggregateId.generate(), login, pass, System.currentTimeMillis());
        userRepository.save(user);

        domainEventPublisher.publish(new UserCreatedEvent(user.getAggregateId()));

        return user.getAggregateId();
    }

    @Override
    public User get(AggregateId aggregateId) {
        return userRepository.get(aggregateId);
    }

}
