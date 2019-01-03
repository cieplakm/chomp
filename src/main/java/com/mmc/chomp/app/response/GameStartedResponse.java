package com.mmc.chomp.app.response;

public class GameStartedResponse extends Response {

    private boolean isMyTurn;
    private GameState gameState;

    public GameStartedResponse(String gameId, boolean isMyTurn, GameState gameState) {
        super("GAME_STARTED", gameId);
        this.isMyTurn = isMyTurn;
        this.gameState = gameState;
    }

    public GameStartedResponse() {
       setType("GAME_STARTED");
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
