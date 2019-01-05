package com.mmc.chomp.app.game.application.command.impl;

import com.mmc.chomp.app.game.application.command.Command;
import com.mmc.chomp.app.game.domain.AggregateId;
import com.mmc.chomp.app.game.domain.DefaultWaitingList;
import com.mmc.chomp.app.game.domain.WaitingList;
import com.mmc.chomp.app.game.domain.board.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class WantToPlayCommand extends Command {
    private int rows;
    private int cols;

    @Autowired
    private WaitingList waitingList;

    @Override
    public void execute() {
        waitingList.signToWaitingList(AggregateId.create(getUserId()), new Size(getRows(), getCols()));
    }
}
