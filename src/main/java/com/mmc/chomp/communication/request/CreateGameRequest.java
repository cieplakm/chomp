package com.mmc.chomp.communication.request;

import lombok.Data;

@Data
public class CreateGameRequest extends RequestDto {
    private int rows;

    private int cols;
}
