package com.mmc.chomp.app.game.application.command.impl;

import com.mmc.chomp.app.game.application.command.Command;
import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.WaiterForGame;
import com.mmc.chomp.app.game.domain.board.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class WantToPlayCommand extends Command {
    private int rows;
    private int cols;

    @Autowired
    private WaiterForGame waiterForGame;

    @Override
    public void execute() {
        waiterForGame.signToWaiterList(AggregateId.create(getUserId()), new Size(getRows(), getCols()));
    }
}
