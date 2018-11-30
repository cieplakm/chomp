package com.mmc.chomp.app.game.domain.ranking;

import com.mmc.chomp.ddd.annotation.domain.support.domain.BaseAgregateRoot;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.PlayerData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Rank extends BaseAgregateRoot {
    private PlayerData playerData;
    private long rank;

    public Rank(AggregateId aggregateId, PlayerData playerData, long rank) {
        this.aggregateId = aggregateId;
        this.playerData = playerData;
        this.rank = rank;
    }

    public Rank() {
    }

    public void up() {
        rank++;
        log.info("Rank for {} was increased", playerData.getAggregateId().getId());
    }

    public void down() {
        rank--;
        log.info("Rank for {} was decreased", playerData.getAggregateId().getId());
    }

    public long getRank(){
        return rank;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }
}
