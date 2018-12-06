package com.mmc.chomp.communication.response;

import lombok.Value;

@Value
public class CreateGameResponse extends Response {
    private String gameId;
}
