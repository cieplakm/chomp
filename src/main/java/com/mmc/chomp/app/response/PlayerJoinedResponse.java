package com.mmc.chomp.app.response;

public class PlayerJoinedResponse extends Response {
    private String joinerId;

    public PlayerJoinedResponse(String gameId, String joinerId) {
        super("PLAYER_JOINED", gameId);
        this.joinerId = joinerId;
    }

    public PlayerJoinedResponse() {
        setType("PLAYER_JOINED");
    }

    public String getJoinerId() {
        return joinerId;
    }

    void setJoinerId(String joinerId) {
        this.joinerId = joinerId;
    }
}
