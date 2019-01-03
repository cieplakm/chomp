package com.mmc.chomp.app.game.domain.ranking;

import com.mmc.chomp.ddd.support.domain.BaseAggregateRoot;
import com.mmc.chomp.app.game.domain.AggregateId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Rank extends BaseAggregateRoot {
    private AggregateId playerId;
    private long rank;

    public Rank(AggregateId aggregateId, AggregateId playerId, long rank) {
        this.aggregateId = aggregateId;
        this.playerId = playerId;
        this.rank = rank;
    }

    public Rank() {
    }

    public void up() {
        rank++;
        log.info("Rank for {} was increased", playerId.getId());
    }

    public void down() {
        rank--;
        log.info("Rank for {} was decreased", playerId.getId());
    }

    public long getRank(){
        return rank;
    }

    public AggregateId playerId() {
        return playerId;
    }
}
