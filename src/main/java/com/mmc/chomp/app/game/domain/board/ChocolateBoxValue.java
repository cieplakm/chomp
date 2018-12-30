package com.mmc.chomp.app.game.domain.board;

import lombok.Value;

@Value
public class ChocolateBoxValue {
    private boolean[][] chocolateValue;
    private int rows;
    private int cols;
}
