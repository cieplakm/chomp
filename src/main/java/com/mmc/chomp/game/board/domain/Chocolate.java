package com.mmc.chomp.game.board.domain;

import com.mmc.chomp.game.sharedkernel.Position;

class Chocolate {
    private Position position;
    private boolean isTaken;
    private boolean isPoison;

    Chocolate(Position position) {
        this.position = position;
    }

    void take(){
        isTaken = true;
    }

    Position getPosition(){
        return position;
    }

    boolean isTaken() {
        return isTaken;
    }
}
