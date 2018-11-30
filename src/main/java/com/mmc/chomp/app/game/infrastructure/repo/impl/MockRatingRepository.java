package com.mmc.chomp.app.game.infrastructure.repo.impl;

import com.mmc.chomp.app.game.domain.ranking.Rank;
import com.mmc.chomp.app.game.domain.ranking.RankingRepository;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.ddd.annotation.domain.support.infrastructure.repository.GenericAggregateRepository;

public class MockRatingRepository implements RankingRepository {
    private GenericAggregateRepository<Rank> rankRepository = new GenericAggregateRepository<>();

    @Override
    public Rank getByParticipant(AggregateId participantId) {
        return rankRepository.get(participantId);
    }

    @Override
    public void save(Rank rank) {
        rankRepository.save(rank.getPlayerData().getAggregateId(), rank);
    }

}
