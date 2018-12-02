package com.mmc.chomp.board;

import com.mmc.chomp.app.game.domain.board.Board;
import com.mmc.chomp.app.game.domain.board.BoardFactory;

import com.mmc.chomp.app.game.domain.board.Size;
import com.mmc.chomp.app.game.domain.board.ChocolateBoxValue;
import com.mmc.chomp.app.game.domain.board.ChocolateValue;
import com.mmc.chomp.app.sharedkernel.Position;
import com.mmc.chomp.app.sharedkernel.exceptions.ChocolateTakenException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BoardTests {

    @Test
    public void shouldPeakFormPointToRightAndFromPointToBottom() throws ChocolateTakenException {
        int row = 5;
        int col = 5;
        Position position = new Position(2, 2);

        Board chompBoard = BoardFactory.create(new Size(row, col));
        chompBoard.peakChocolate(position);
        ChocolateBoxValue snapshot = chompBoard.snapshot();

        ChocolateValue[][] chocolateValue = snapshot.getChocolateValue();

        Assertions.assertThat(chocolateValue[2][2].isTaken()).isTrue();
        Assertions.assertThat(chocolateValue[2][3].isTaken()).isTrue();
        Assertions.assertThat(chocolateValue[3][2].isTaken()).isTrue();
        Assertions.assertThat(chocolateValue[2][1].isTaken()).isFalse();
        Assertions.assertThat(chocolateValue[1][2].isTaken()).isFalse();
    }

    @Test
    public void cannotPeakSameChocolateTwice() throws ChocolateTakenException {
        int row = 5;
        int col = 5;
        Position position = new Position(2, 2);

        Board chompBoard = BoardFactory.create(new Size(row, col));
        chompBoard.peakChocolate(position);
        Assertions.assertThatThrownBy(() -> chompBoard.peakChocolate(position)).isInstanceOf(ChocolateTakenException.class);

    }
}
