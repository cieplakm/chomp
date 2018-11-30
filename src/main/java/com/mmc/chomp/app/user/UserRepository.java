package com.mmc.chomp.app.user;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;

public interface UserRepository {
    void save(User user);
    User get(AggregateId id);
}
