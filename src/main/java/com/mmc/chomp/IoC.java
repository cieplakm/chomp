package com.mmc.chomp;



public class IoC {
//    public static DefaultDomainEventPublisher domainEventPublisher ;
//    public static GameRepository gameRepository;
//    public static  RankingRepository rankingRepository;
//
//    public static DefaultDomainEventPublisher domainEventPublisher(){
//        if (domainEventPublisher == null) {
//            DefaultRankingService rankingService = new DefaultRankingService(rankingRepository);
//            domainEventPublisher = new DefaultDomainEventPublisher(new DefaultRankingService(getRankingRepository()), new TurnChangingListener(), new UserCreatedListener(rankingService), gameCreatedEventHandler, new GameOverListener(rankingService));
//        }
//        return domainEventPublisher;
//    }
//
//    public static GameRepository gameRepository(){
//        if (gameRepository == null){
//            gameRepository= new MockGameRepository();
//        }
//        return gameRepository;
//    }
//
//    public static RankingRepository getRankingRepository() {
//        if (rankingRepository == null){
//            rankingRepository = new MockRatingRepository();
//        }
//        return rankingRepository;
//    }
}
