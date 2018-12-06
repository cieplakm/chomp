package com.mmc.chomp.communication.request;

import lombok.Data;

@Data
public class StateRequest extends RequestDto {
    private String gameId;
}
