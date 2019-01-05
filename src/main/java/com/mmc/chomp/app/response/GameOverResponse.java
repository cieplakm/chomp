package com.mmc.chomp.app.response;

public class GameOverResponse extends Response {
    private boolean isWon;

    public GameOverResponse(String gameId, boolean isWon) {
        super("GAME_OVER", gameId);

        this.isWon = isWon;
    }

    public GameOverResponse() {
        setType("GAME_OVER");
    }

     public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean won) {
        isWon = won;
    }
}
