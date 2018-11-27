package com.mmc.chomp.game.board.domain;

import com.mmc.chomp.IoC;
import com.mmc.chomp.ddd.annotation.domain.support.DomainEventPublisher;
import com.mmc.chomp.ddd.annotation.domain.support.PoisonLeftEvent;
import com.mmc.chomp.game.board.values.ChocolateBoxValue;
import com.mmc.chomp.game.sharedkernel.Position;
import com.mmc.chomp.game.sharedkernel.exceptions.ChocolateTakenException;

public class ChompBoard {
    private ChocolateBox box;
    private Picker picker = new Picker();

    private DomainEventPublisher domainEventPublisher = IoC.domainEventPublisher;

    ChompBoard(ChocolateBox box) {
        this.box = box;
    }

    public void peakChocolate(Position position){
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

    private void checkIfIsPossiblePeakChocolate(Position position) {
        if (box.getChocolateAt(position).isTaken()) {
            throw new ChocolateTakenException();
        }
    }

    public ChocolateBoxValue snapshot(){
        return box.snapshot();
    }
}
