package com.mmc.chomp.app.response;

public class GameStartedResponse extends Response {

    private final String gameId;

    public GameStartedResponse(String gameId) {
        super("GAME_STARTED");
        this.gameId = gameId;

    }

    public String getGameId() {
        return gameId;
    }

}
