package com.mmc.chomp.board;

import org.junit.Test;


public class BoardTests {
//
//    @Test
//    public void shouldPeakFormPointToRightAndFromPointToBottom(){
//        int row = 5;
//        int col = 5;
//        Position position = new Position(2, 2);
//
//        ChompBoard chompBoard = BoardFactory.create(row, col);
//        chompBoard.peakChocolate(position);
//        ChocolateBoxValue snapshot = chompBoard.snapshot();
//
//        ChocolateValue[][] chocolateValue = snapshot.getChocolateValue();
//
//        Assertions.assertThat(chocolateValue[2][2].isTaken()).isTrue();
//        Assertions.assertThat(chocolateValue[2][3].isTaken()).isTrue();
//        Assertions.assertThat(chocolateValue[3][2].isTaken()).isTrue();
//        Assertions.assertThat(chocolateValue[2][1].isTaken()).isFalse();
//        Assertions.assertThat(chocolateValue[1][2].isTaken()).isFalse();
//    }

    @Test
    public void cannotPeakSameChocolateTwice(){
        String v = v();

    }

    public String v(){
        String s = null;

        switch(s){
            case "SSS": return "B";
            default: return "A";
        }
    }
}
