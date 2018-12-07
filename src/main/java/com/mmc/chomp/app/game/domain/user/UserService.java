package com.mmc.chomp.app.game.domain.user;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;

public interface UserService {
    AggregateId create(String login, String pass);
    User get(AggregateId aggregateId);
}
