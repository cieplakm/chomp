package com.mmc.chomp.app.game.domain.board;

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

    public boolean isPoison() {
        return isPoison;
    }
}
