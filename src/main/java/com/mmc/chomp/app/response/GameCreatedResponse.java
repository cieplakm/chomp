package com.mmc.chomp.app.response;

public class GameCreatedResponse extends Response {

    public GameCreatedResponse() {
        super("GAME_CREATED");
    }

    private String gameId;

}
