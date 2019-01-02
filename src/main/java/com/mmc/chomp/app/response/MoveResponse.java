package com.mmc.chomp.app.response;

public class MoveResponse extends Response {

    private boolean[][] boardState;
    private boolean isMyTourTurn;

    public MoveResponse(String gameId, boolean[][] boardState, boolean isMyTurn) {
        super("MOVE", gameId);
        this.boardState = boardState;
        this.isMyTourTurn = isMyTurn;
    }

    public MoveResponse() {
        setType("MOVE");
    }

    public boolean[][] getBoardState() {
        return boardState;
    }

    public boolean isMyTourTurn() {
        return isMyTourTurn;
    }

    public void setMyTourTurn(boolean myTourTurn) {
        isMyTourTurn = myTourTurn;
    }
}
