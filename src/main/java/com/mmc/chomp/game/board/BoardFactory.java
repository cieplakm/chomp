package com.mmc.chomp.game.board;

public class BoardFactory {
    public static ChompBoard create(int rows, int cols){
        Size size = new Size(rows, cols);
        ChocolateBox chocolateBox = new ChocolateBox(size);
        return new ChompBoard(chocolateBox);
    }

}
