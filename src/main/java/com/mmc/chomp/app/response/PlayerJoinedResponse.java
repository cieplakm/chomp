package com.mmc.chomp.app.response;

public class PlayerJoinedResponse extends Response {

    public PlayerJoinedResponse() {
        super("PLAYER_JOINED");
    }

    private String gameId;
    private String joinerId;

}
