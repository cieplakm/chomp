package com.mmc.chomp.communication.request;

import lombok.Data;

@Data
public class MoveRequest extends RequestDto {
    private String gameId;
    private int row;
    private int col;
}
