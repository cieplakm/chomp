package com.mmc.chomp.app.game.infrastructure.repo.impl;

import com.mmc.chomp.app.game.domain.ranking.Rank;
import com.mmc.chomp.app.game.domain.ranking.RankingRepository;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.ddd.support.repository.GenericAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.mmc.chomp.app.game.infrastructure.repo.impl.MockUserRepository.MOCK_USER_ID_1;
import static com.mmc.chomp.app.game.infrastructure.repo.impl.MockUserRepository.MOCK_USER_ID_2;

@Component
public class MockRatingRepository implements RankingRepository {
    private GenericAggregateRepository<Rank> rankRepository = new GenericAggregateRepository<>();

    @Autowired
    public MockRatingRepository() {
        rankRepository.save(MOCK_USER_ID_1, new Rank(MOCK_USER_ID_1, MOCK_USER_ID_1,1000L));
        rankRepository.save(MOCK_USER_ID_2, new Rank(MOCK_USER_ID_2, MOCK_USER_ID_2,1000L));
    }

    @Override
    public Rank getByParticipant(AggregateId participantId) {
        return rankRepository.get(participantId);
    }

    @Override
    public void save(Rank rank) {
        rankRepository.save(rank.playerId(), rank);
    }

}
