package com.mmc.chomp.app.game.application.api.service;

import com.mmc.chomp.app.sharedkernel.Player;
import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.sharedkernel.Position;

public interface GameService {

    AggregateId createGame(Player creator, Size boardSize);

    void joinToGame(AggregateId aggregateId, Player player);

    void move(AggregateId aggregateId, Position position);

    void start(AggregateId id);
}
