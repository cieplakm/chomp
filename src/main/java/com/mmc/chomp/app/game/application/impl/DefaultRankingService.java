package com.mmc.chomp.app.game.application.impl;

import com.mmc.chomp.app.game.application.api.service.RankingService;
import com.mmc.chomp.app.game.domain.ranking.Rank;
import com.mmc.chomp.app.game.domain.ranking.RankingRepository;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.Incubating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

@Slf4j
@Service
public class DefaultRankingService implements RankingService {

    private RankingRepository rankingRepository;

    @Autowired
    public DefaultRankingService(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @Override
    public void changeRanking(AggregateId winnerId, AggregateId looserId) {
        Rank winnerRank = rankingRepository.getByParticipant(winnerId);
        Rank looserRank = rankingRepository.getByParticipant(looserId);

        winnerRank.up();
        looserRank.down();

//        rankingRepository.save(winnerRank);
//        rankingRepository.save(looserRank);

        log.info("Ranking changed for {}. Now is: {}", winnerRank.playerId().getId(), winnerRank.getRank());
        log.info("Ranking changed for {}. Now is: {}", looserRank.playerId().getId(), looserRank.getRank());
    }

    @Override
    public void create(AggregateId playerId) {
        Rank rank = new Rank(AggregateId.generate(), playerId, 1000);
        rankingRepository.save(rank);
        log.info("Ranking created for {}", playerId.getId());
    }

    @Override
    public Rank get(AggregateId userId) {
        return rankingRepository.getByParticipant(userId);
    }

}
