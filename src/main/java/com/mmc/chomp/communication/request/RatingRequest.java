package com.mmc.chomp.communication.request;

import lombok.Data;

@Data
public class RatingRequest extends RequestDto {
    private String userId;
}
