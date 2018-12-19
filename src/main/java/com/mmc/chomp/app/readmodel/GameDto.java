package com.mmc.chomp.app.readmodel;

import lombok.Value;

@Value
public class GameDto {
    private boolean[][] board;
    private String status;
    private String winner;
    private String player1;
    private String player2;
}
