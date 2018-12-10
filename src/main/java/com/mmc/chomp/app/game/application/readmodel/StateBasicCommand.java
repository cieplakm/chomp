package com.mmc.chomp.app.game.application.readmodel;

import com.mmc.chomp.app.game.application.command.Command;
import lombok.Data;

@Data
public class StateBasicCommand extends Command {
    private String gameId;

    @Override
    public void execute() {

    }
}
