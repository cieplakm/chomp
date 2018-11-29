package com.mmc.chomp.app.ranking.application.api;

import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;

public interface RankingService {
    void changeRanking(AggregateId gameId);
}
