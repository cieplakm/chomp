package com.mmc.chomp.app.game.domain;

import com.mmc.chomp.app.game.domain.board.Size;

public interface WaitingList {
    void signToWaitingList(AggregateId userId, Size size);
}
