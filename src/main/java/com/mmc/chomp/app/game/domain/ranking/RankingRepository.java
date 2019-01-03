package com.mmc.chomp.app.game.domain.ranking;

import com.mmc.chomp.app.game.domain.AggregateId;

public interface RankingRepository {
    Rank getByParticipant(AggregateId participantId);
    void save(Rank rank);
}
