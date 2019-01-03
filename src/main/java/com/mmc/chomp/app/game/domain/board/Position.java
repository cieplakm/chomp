package com.mmc.chomp.app.game.domain.board;

import lombok.Value;

@Value
public class Position {
    public static final Position POISON_POSITION = new Position(0,0);
    public static final Position AT_RIGHT_OF_POISON_POSITION = new Position(0,1);
    public static final Position AT_BOTTOM_OF_POISON_POSITION = new Position(1,0);

    private int row;
    private int col;
}
