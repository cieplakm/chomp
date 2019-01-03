package com.mmc.chomp.app.response;

public class PlayerJoinedResponse extends Response {
    private GameState gameState;

    public PlayerJoinedResponse(String gameId, GameState gameState) {
        super("PLAYER_JOINED", gameId);
        this.gameState = gameState;
    }

    public PlayerJoinedResponse() {
        setType("PLAYER_JOINED");
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
