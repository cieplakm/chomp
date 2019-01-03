package com.mmc.chomp.app.game.domain.user;

import com.mmc.chomp.app.game.domain.AggregateId;

public interface UserRepository {
    void save(User user);
    User get(AggregateId id);
}
