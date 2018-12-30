package com.mmc.chomp.app.response;

public class PlayerLeftResponse extends Response {
    private String leaver;
    private String stayer;

    public PlayerLeftResponse(String gameId, String stayer, String leaver) {
        super("PLAYER_LEFT", gameId);
        this.stayer = stayer;
        this.leaver = leaver;
    }

    public PlayerLeftResponse() {
        setType("PLAYER_LEFT");
    }

    public String getLeaver() {
        return leaver;
    }

    public void setLeaver(String leaver) {
        this.leaver = leaver;
    }

    public String getStayer() {
        return stayer;
    }

    public void setStayer(String stayer) {
        this.stayer = stayer;
    }
}
