package com.mmc.chomp.app.response;

public class GameOverResponse extends Response {

    private final String gameId;
    private final String winnerId;
    private final String looserId;

    public GameOverResponse(String gameId, String winnerId, String looserId) {
        super("GAME_OVER");
        this.gameId = gameId;
        this.winnerId = winnerId;
        this.looserId = looserId;
    }

    public String getGameId() {
        return gameId;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public String getLooserId() {
        return looserId;
    }

}
