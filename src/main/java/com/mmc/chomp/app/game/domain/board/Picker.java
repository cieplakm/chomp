package com.mmc.chomp.app.game.domain.board;

import com.mmc.chomp.app.sharedkernel.Position;

class Picker {
    void pick(ChocolateBox chocolateBox, Position chocolatePosition) {
        for (int row = chocolatePosition.getRow(); row < chocolateBox.getSize().getRows(); row++){
            Chocolate[] chocolateRow = chocolateBox.getChocolates()[row];

            for (int col = chocolatePosition.getCol(); col< chocolateBox.getSize().getCols(); col++){
                Chocolate chocolate = chocolateRow[col];
                chocolate.take();
            }
        }
    }
}
