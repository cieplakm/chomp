package com.mmc.chomp.app.response;

public class GameCreatedResponse extends Response {
    private int rows;
    private int cols;
    private boolean[][] board;

    public GameCreatedResponse(String gameId, int rows, int cols, boolean[][] board) {
        super("GAME_CREATED", gameId);
        this.rows = rows;
        this.cols = cols;
        this.board = board;
    }

    public GameCreatedResponse() {
        setType("GAME_CREATED");
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean[][] getBoard() {
        return board;
    }

    void setRows(int rows) {
        this.rows = rows;
    }

    void setCols(int cols) {
        this.cols = cols;
    }

    void setBoard(boolean[][] board) {
        this.board = board;
    }
}
