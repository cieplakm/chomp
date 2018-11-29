package com.mmc.chomp.app.game.application.api.service;

import com.mmc.chomp.app.sharedkernel.Participant;
import com.mmc.chomp.ddd.annotation.domain.support.AggregateId;
import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.sharedkernel.Position;

public interface GameService {

    AggregateId createGame(Participant creator, Size boardSize);

    void joinToGame(AggregateId aggregateId, Participant participant);

    void move(AggregateId aggregateId, Position position);

    void start(AggregateId id);
}
