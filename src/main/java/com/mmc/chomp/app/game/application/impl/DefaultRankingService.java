package com.mmc.chomp.app.game.application.impl;

import com.mmc.chomp.IoC;
import com.mmc.chomp.app.game.domain.game.Game;
import com.mmc.chomp.app.game.domain.game.GameRepository;

import com.mmc.chomp.app.game.application.api.service.RankingService;
import com.mmc.chomp.app.game.domain.ranking.Rank;
import com.mmc.chomp.app.game.domain.ranking.RankingRepository;
import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;
import com.mmc.chomp.ddd.annotation.domain.support.ParticipantData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultRankingService implements RankingService {
    private GameRepository gameRepository = IoC.gameRepository();
    private RankingRepository rankingRepository = IoC.getRankingRepository();

    @Override
    public void changeRanking(AggregateId gameId) {
        Game game = gameRepository.get(gameId);
        ParticipantData winner = game.getWinner();

        Rank rank = rankingRepository.getByParticipant(winner.getAggregateId());

        rank.up();
        log.info("Ranking is {}", rank.getRank());
        rankingRepository.save(rank);
        log.info("Ranking Changed!");
    }

}
