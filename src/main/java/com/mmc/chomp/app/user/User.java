package com.mmc.chomp.app.user;

import com.mmc.chomp.ddd.annotation.domain.support.domain.BaseAgregateRoot;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import lombok.Value;

@Value
public class User extends BaseAgregateRoot {
    private String login;
    private String password;
    private long registerDate;

    public User(AggregateId aggregateId, String login, String password, long registerDate) {
        this.aggregateId = aggregateId;
        this.login = login;
        this.password = password;
        this.registerDate = registerDate;
    }
}
