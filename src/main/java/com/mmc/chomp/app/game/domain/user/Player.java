package com.mmc.chomp.app.game.domain.user;

import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.ddd.support.domain.BaseAggregateRoot;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Player extends BaseAggregateRoot {
    private String login;

    public Player(AggregateId aggregateId, String login) {
        this.aggregateId = aggregateId;
        this.login = login;
    }
}
