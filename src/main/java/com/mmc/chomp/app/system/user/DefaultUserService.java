package com.mmc.chomp.app.system.user;

import com.mmc.chomp.app.canonicalmodel.events.UserCreatedEvent;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.PlayerData;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
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

        domainEventPublisher.event(new UserCreatedEvent(new PlayerData(user.getAggregateId(), user.getLogin())));

        return user.getAggregateId();
    }


}
