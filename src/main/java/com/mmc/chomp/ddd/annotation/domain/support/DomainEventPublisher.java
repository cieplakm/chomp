package com.mmc.chomp.ddd.annotation.domain.support;

import com.mmc.chomp.IoC;
import com.mmc.chomp.game.Participant;
import com.mmc.chomp.game.board.application.impl.Game;
import com.mmc.chomp.game.board.application.persistence.GameRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainEventPublisher {
    private GameRepository gameRepository = IoC.gameRepository();

    public void event(Event event){
        Game game = gameRepository.get(event.getEmbeddedId());
        Participant participant = game.currentTurn();
        log.info("Game won " + participant.getLogin());
    }
}
