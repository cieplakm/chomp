package com.mmc.chomp.app.game.domain.board;

public class BoardFactory {

    public static Board create(Size size) {
        ChocolateBox chocolateBox = new ChocolateBox(size);
        return new Board(chocolateBox);
    }
}
