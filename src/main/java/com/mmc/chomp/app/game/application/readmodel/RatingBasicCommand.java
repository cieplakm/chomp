package com.mmc.chomp.app.game.application.readmodel;

import com.mmc.chomp.app.game.application.command.Command;
import lombok.Data;

@Data
public class RatingBasicCommand extends Command {
    private String userId;

    @Override
    public void execute() {

    }
}
