package com.mmc.chomp.app.response;

public class GameOverResponse extends Response {
    private  String winnerId;
    private  String looserId;

    public GameOverResponse(String gameId, String winnerId, String looserId) {
        super("GAME_OVER", gameId);
        this.winnerId = winnerId;
        this.looserId = looserId;
    }

    public GameOverResponse() {
        setType("GAME_OVER");
    }

    public String getWinnerId() {
        return winnerId;
    }

    public String getLooserId() {
        return looserId;
    }

    void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }

    void setLooserId(String looserId) {
        this.looserId = looserId;
    }
}
