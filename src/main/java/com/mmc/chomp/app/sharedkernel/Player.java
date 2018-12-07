package com.mmc.chomp.app.sharedkernel;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.PlayerData;
import com.mmc.chomp.ddd.support.domain.BaseAggregateRoot;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Player extends BaseAggregateRoot {
    private String login;

    public Player(AggregateId aggregateId, String login) {
        this.aggregateId = aggregateId;
        this.login = login;
    }

    public PlayerData snapshot() {
        return new PlayerData(aggregateId, login);
    }

}
