package com.mmc.chomp.app.response;

public class GameStartedResponse extends Response {

    public GameStartedResponse(String gameId) {
        super("GAME_STARTED", gameId);
    }

    public GameStartedResponse() {
       setType("GAME_STARTED");
    }
}
