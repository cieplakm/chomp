package com.mmc.chomp.app.game.domain.game;

import java.util.Random;

class TurnChanger {

    private TurnChanger(){
    }

    static <T> T drawLotsPlayer(T t1, T t2) {
        boolean b = new Random().nextBoolean();
        return b ? t1 : t2;
    }

    static <T> T switchTurn(T currentP, T p1, T p2) {
        return currentP.equals(p1) ? p2 : p1;
    }
}
