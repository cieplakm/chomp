package com.mmc.chomp.app.response;

public class GameCreatedResponse extends Response {
    private GameState gameState;

    public GameCreatedResponse(String gameId, GameState gameState) {
        super("GAME_CREATED", gameId);
        this.gameState = gameState;
    }

    public GameCreatedResponse() {
        setType("GAME_CREATED");
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
