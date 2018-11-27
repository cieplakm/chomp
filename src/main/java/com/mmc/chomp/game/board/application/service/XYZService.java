package com.mmc.chomp.game.board.application.service;

import com.mmc.chomp.game.Participant;
import com.mmc.chomp.ddd.annotation.domain.support.EmbeddedId;
import com.mmc.chomp.game.board.domain.Size;
import com.mmc.chomp.game.sharedkernel.Position;

public interface XYZService {

    EmbeddedId createGame(Participant creator, Size boardSize);

    void joinToGame(EmbeddedId embeddedId, Participant participant);

    void move(EmbeddedId embeddedId, Position position);

    void start(EmbeddedId id);
}
