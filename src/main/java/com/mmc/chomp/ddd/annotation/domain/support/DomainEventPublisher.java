package com.mmc.chomp.ddd.annotation.domain.support;

import com.mmc.chomp.IoC;
import com.mmc.chomp.game.game.Game;
import com.mmc.chomp.game.board.application.persistence.GameRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainEventPublisher {
    private GameRepository gameRepository;

    public DomainEventPublisher() {
        gameRepository = IoC.gameRepository();
    }

    public void event(GameOver event){
        Game game = gameRepository.get(event.getEmbeddedId());
        log.info("Game won " + event.getParticipantData());
    }
}
