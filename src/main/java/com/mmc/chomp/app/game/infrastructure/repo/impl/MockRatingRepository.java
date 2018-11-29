package com.mmc.chomp.app.game.infrastructure.repo.impl;

import com.mmc.chomp.app.game.domain.ranking.RankingRepository;
import com.mmc.chomp.app.game.domain.ranking.Rank;
import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;

import java.util.HashMap;
import java.util.Map;

public class MockRatingRepository implements RankingRepository {
    private RankRepository rankRepository = new RankRepository();

    @Override
    public Rank getByParticipant(AggregateId participantId) {
        if (rankRepository.get(participantId) == null){
            rankRepository.save(Rank.create(participantId));
        }
        return rankRepository.get(participantId);
    }

    @Override
    public void save(Rank rank) {
        rankRepository.save(rank);
    }

    public static class RankRepository {

        private Map<AggregateId, Rank> map = new HashMap<>();

        private Rank get(AggregateId aggregateId) {
            return map.get(aggregateId);
        }

        private void save(Rank rank) {
            map.put(rank.getParticipantData().getAggregateId(), rank);
        }

    }
}
