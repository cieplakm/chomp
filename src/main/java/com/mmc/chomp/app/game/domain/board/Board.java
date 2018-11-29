package com.mmc.chomp.app.game.domain.board;

import com.mmc.chomp.app.values.ChocolateBoxValue;
import com.mmc.chomp.app.sharedkernel.Position;
import com.mmc.chomp.app.sharedkernel.exceptions.ChocolateTakenException;

public class Board {
    private ChocolateBox box;
    private Picker picker = new Picker();

    Board(ChocolateBox box) {
        this.box = box;
    }

    public void peakChocolate(Position position) throws ChocolateTakenException {
        checkIfIsPossiblePeakChocolate(position);

        peak(position);
    }

    public boolean checkIfPoisionLeft() {
        return box.getChocolateAt(Position.AT_RIGHT_OF_POISON_POSITION).isTaken()
                && box.getChocolateAt(Position.AT_BOTTOM_OF_POISON_POSITION).isTaken();
    }

    private void peak(Position position) {
       picker.pick(box,position);
    }

    private void checkIfIsPossiblePeakChocolate(Position position) throws ChocolateTakenException {
        if (box.getChocolateAt(position).isTaken()) {
            throw new ChocolateTakenException();
        }
    }

    public ChocolateBoxValue snapshot(){
        return box.snapshot();
    }
}
