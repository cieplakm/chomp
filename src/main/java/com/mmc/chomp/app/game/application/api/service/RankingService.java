package com.mmc.chomp.app.game.application.api.service;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.domain.ranking.Rank;

public interface RankingService {

    void changeRanking(AggregateId winnerId, AggregateId looserId);

    void create(AggregateId userId);

    Rank get(AggregateId userId);
}
