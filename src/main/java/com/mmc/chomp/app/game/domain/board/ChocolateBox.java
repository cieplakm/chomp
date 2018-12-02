package com.mmc.chomp.app.game.domain.board;



import com.mmc.chomp.app.sharedkernel.Position;

class ChocolateBox {
    private Size size;
    private Chocolate[][] chocolates;

    ChocolateBox(Size size) {
        this.size = size;
        chocolates = createChocolates(size);
    }

    private Chocolate[][] createChocolates(Size size) {
        Chocolate[][] chocolates = new Chocolate[size.getRows()][size.getCols()];
        for (int i = 0; i < size.getRows(); i++) {
            for (int k = 0; k < size.getCols(); k++) {
                chocolates[i][k] = new Chocolate(new Position(i, k));
            }
        }
        return chocolates;
    }

    Chocolate[][] getChocolates() {
        return chocolates;
    }

    Size getSize() {
        return size;
    }

    ChocolateBoxValue snapshot() {
        ChocolateValue[][] chocolateValues = new ChocolateValue[getSize().getRows()][getSize().getCols()];

        for (int i = 0; i < chocolates.length; i++) {
            Chocolate[] chocolate = chocolates[i];

            for (int k = 0; k < chocolate.length; k++) {
                Chocolate chocolate1 = chocolate[k];
                chocolateValues[i][k] = new ChocolateValue(chocolate1.isTaken());
            }

        }
        return new ChocolateBoxValue(chocolateValues);
    }

    Chocolate getChocolateAt(Position position) {
        return chocolates[position.getRow()][position.getCol()];
    }
}
