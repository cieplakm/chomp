package com.mmc.chomp.app.game.domain.board;

public class BoardFactory {
    public static Board create(int rows, int cols){
        ChocolateBox chocolateBox = new ChocolateBox(new Size(rows, cols));
        return new Board(chocolateBox);
    }

}
