package com.mmc.chomp;


import com.mmc.chomp.app.game.domain.game.GameRepository;
import com.mmc.chomp.app.game.infrastructure.repo.impl.MockGameRepository;
import com.mmc.chomp.app.game.infrastructure.repo.impl.MockRatingRepository;
import com.mmc.chomp.app.game.domain.ranking.RankingRepository;
import com.mmc.chomp.app.system.infrastructure.event.impl.DefaultDomainEventPublisher;

public class IoC {
    public static DefaultDomainEventPublisher domainEventPublisher ;
    public static GameRepository gameRepository;
    public static  RankingRepository rankingRepository;

    public static DefaultDomainEventPublisher domainEventPublisher(){
        if (domainEventPublisher == null) domainEventPublisher = new DefaultDomainEventPublisher();
        return domainEventPublisher;
    }

    public static GameRepository gameRepository(){
        if (gameRepository == null){
            gameRepository= new MockGameRepository();
        }
        return gameRepository;
    }

    public static RankingRepository getRankingRepository() {
        if (rankingRepository == null){
            rankingRepository = new MockRatingRepository();
        }
        return rankingRepository;
    }
}
