package com.mmc.chomp.app.response;

public class MoveResponse extends Response {

    private GameState gameState;
    private boolean isMyTourTurn;

    public MoveResponse(String gameId, GameState gameState, boolean isMyTurn) {
        super("MOVE", gameId);
        this.gameState = gameState;
        this.isMyTourTurn = isMyTurn;
    }

    public MoveResponse() {
        setType("MOVE");
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isMyTourTurn() {
        return isMyTourTurn;
    }

    public void setMyTourTurn(boolean myTourTurn) {
        isMyTourTurn = myTourTurn;
    }
}
