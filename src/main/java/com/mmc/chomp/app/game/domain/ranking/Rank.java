package com.mmc.chomp.app.game.domain.ranking;

public class Rank {

    public void increase(){
        points++;
    }

    public void decrease(){
        points--;
    }

    private int points;
}
