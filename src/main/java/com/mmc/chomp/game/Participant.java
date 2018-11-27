package com.mmc.chomp.game;

import com.mmc.chomp.game.board.application.impl.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Participant {
    private EmbeddedId embeddedId;

    private String login;

}
