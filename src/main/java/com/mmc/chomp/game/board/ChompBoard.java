package com.mmc.chomp.game.board;

import com.mmc.chomp.ddd.annotation.domain.Agregate;
import com.mmc.chomp.game.board.exceptions.ChocolateTakenException;
import com.mmc.chomp.game.board.values.ChocolateBoxValue;

@Agregate
public class ChompBoard {
    private ChocolateBox box;
    private Picker picker = new Picker();

    ChompBoard(ChocolateBox box) {
        this.box = box;
    }

    public void peakChocolate(Position position){
        checkIfIsPossiblePeakChocolate(position);
        peak(position);
    }

    private void peak(Position position) {
       picker.pick(box,position);
    }

    private void checkIfIsPossiblePeakChocolate(Position position) {
        if (box.getChocolateAt(position).isTaken()) {
            throw new ChocolateTakenException();
        }
    }

    public ChocolateBoxValue snapshot(){
        return box.snapshot();
    }
}
