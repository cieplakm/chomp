package com.mmc.chomp.app.system.user;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;

public interface UserService {
    AggregateId create(String login, String pass);
    User get();
}
