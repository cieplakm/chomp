package com.mmc.chomp.app.game.application.api.service;

import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;

public interface RankingService {
    void changeRanking(AggregateId gameId);
}
