package com.mmc.chomp.game.board.domain;

public class BoardFactory {
    public static ChompBoard create(Size size){
        ChocolateBox chocolateBox = new ChocolateBox(size);
        return new ChompBoard(chocolateBox);
    }

}
