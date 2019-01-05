package com.mmc.chomp.app.game.domain;

import com.mmc.chomp.app.game.domain.board.Position;
import com.mmc.chomp.app.game.domain.game.Game;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FakePlayer {
    public void move(Game game) {
        boolean[][] chocolateValue = game.snapshot().getBoard().getChocolateValue();

        for (int i = chocolateValue.length - 1; i >= 0; i--) {
            boolean[] booleans = chocolateValue[i];
            for (int k = booleans.length - 1; k >= 0; k--) {
                boolean aBoolean = booleans[k];
                if (!aBoolean){
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("Fake Player just moved position {},{}", i, k);
                    game.move(new Position(i,k));
                    return;
                }
            }
        }


    }
}
