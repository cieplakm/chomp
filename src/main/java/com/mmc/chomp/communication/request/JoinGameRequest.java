package com.mmc.chomp.communication.request;

import lombok.Data;

@Data
public class JoinGameRequest extends RequestDto {
    private String gameId;
}
