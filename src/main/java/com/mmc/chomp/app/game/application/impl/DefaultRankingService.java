package com.mmc.chomp.app.game.application.impl;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.PlayerData;
import com.mmc.chomp.app.game.application.api.service.RankingService;
import com.mmc.chomp.app.game.domain.ranking.Rank;
import com.mmc.chomp.app.game.domain.ranking.RankingRepository;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultRankingService implements RankingService {

    private RankingRepository rankingRepository;

    public DefaultRankingService(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @Override
    public void changeRanking(PlayerData winner, PlayerData looser) {
        Rank winnerRank = rankingRepository.getByParticipant(winner.getAggregateId());
        Rank looserRank = rankingRepository.getByParticipant(looser.getAggregateId());

        winnerRank.up();
        looserRank.down();

        rankingRepository.save(winnerRank);
        rankingRepository.save(looserRank);

        log.info("Ranking changed for {}. Now is: {}", winnerRank.getPlayerData().getAggregateId().getId(), winnerRank.getRank());
        log.info("Ranking changed for {}. Now is: {}", looserRank.getPlayerData().getAggregateId().getId(), looserRank.getRank());
    }

    @Override
    public void create(PlayerData playerData) {
        Rank rank = new Rank(AggregateId.generate(), playerData, 1000);
        rankingRepository.save(rank);
        log.info("Ranking created for {}", playerData.getAggregateId().getId());
    }

}
