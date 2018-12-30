package com.mmc.chomp.app.response;

public class MoveResponse extends Response {

    private boolean[][] boardState;

    public MoveResponse(String gameId, boolean[][] boardState) {
        super("MOVE", gameId);
        this.boardState = boardState;
    }

    public MoveResponse() {
        setType("MOVE");
    }

    public boolean[][] getBoardState() {
        return boardState;
    }
}
