package com.mmc.chomp.app.response;

public class GameState {
    private boolean[][] board;
    private int rows;
    private int cols;
    private String creatorId;
    private String joinerId;
    private String status;

    public GameState() {
    }

    public GameState(boolean[][] board, int rows, int cols, String creatorId, String joinerId, String status) {
        this.board = board;
        this.rows = rows;
        this.cols = cols;
        this.creatorId = creatorId;
        this.joinerId = joinerId;
        this.status = status;
    }

    public boolean[][] getBoard() {
        return board;
    }

    public void setBoard(boolean[][] board) {
        this.board = board;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getJoinerId() {
        return joinerId;
    }

    public void setJoinerId(String joinerId) {
        this.joinerId = joinerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
