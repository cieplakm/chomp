package com.mmc.chomp.app.response;

public class MoveResponse extends Response {

    private final String gameId;
    private final boolean[][] boardState;

    public MoveResponse(String gameId, boolean[][] boardState) {
        super("MOVE");
        this.gameId = gameId;
        this.boardState = boardState;
    }

    public String getGameId() {
        return gameId;
    }

    public boolean[][] getBoardState() {
        return boardState;
    }
}
