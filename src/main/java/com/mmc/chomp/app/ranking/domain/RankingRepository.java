package com.mmc.chomp.app.ranking.domain;

import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;

public interface RankingRepository {
    Rank getByParticipant(AggregateId participantId);
    void save(Rank rank);
}
