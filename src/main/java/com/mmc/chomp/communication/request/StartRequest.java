package com.mmc.chomp.communication.request;

import lombok.Data;

@Data
public class StartRequest extends RequestDto {
    private String gameId;
}
