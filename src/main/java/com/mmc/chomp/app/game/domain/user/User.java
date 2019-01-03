package com.mmc.chomp.app.game.domain.user;

import com.mmc.chomp.ddd.support.domain.BaseAggregateRoot;
import com.mmc.chomp.app.game.domain.AggregateId;
import lombok.Value;

@Value
public class User extends BaseAggregateRoot {
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
