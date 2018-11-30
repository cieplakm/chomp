package com.mmc.chomp.app.canonicalmodel.events;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.PlayerData;
import lombok.Value;

@Value
public class GameOver extends Event {
    private PlayerData winner;
    private PlayerData looser;
}
