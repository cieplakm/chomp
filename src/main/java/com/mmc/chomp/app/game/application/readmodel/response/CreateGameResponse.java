package com.mmc.chomp.app.game.application.readmodel.response;

import lombok.Value;

@Value
public class CreateGameResponse extends Response {
    private String gameId;
}
