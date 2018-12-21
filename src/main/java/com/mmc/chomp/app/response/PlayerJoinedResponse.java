package com.mmc.chomp.app.response;

public class PlayerJoinedResponse extends Response {

    private final String gameId;
    private final String joinerId;

    public PlayerJoinedResponse(String gameId, String joinerId) {
        super("PLAYER_JOINED");
        this.gameId = gameId;
        this.joinerId = joinerId;
    }

    public String getGameId() {
        return gameId;
    }

    public String getJoinerId() {
        return joinerId;
    }
}
