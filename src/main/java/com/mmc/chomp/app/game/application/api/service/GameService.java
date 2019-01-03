package com.mmc.chomp.app.game.application.api.service;

import com.mmc.chomp.app.game.domain.game.GameProjection;
import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.board.Position;

public interface GameService {

    AggregateId createGame(AggregateId creatorId, int rows, int cols);

    void joinToGame(AggregateId userId, AggregateId gameId);

    void move(AggregateId gameId, Position position);

    void start(AggregateId gameId);

    GameProjection projection(AggregateId gameId);
}
