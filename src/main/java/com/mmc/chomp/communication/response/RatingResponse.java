package com.mmc.chomp.communication.response;

import lombok.Value;

@Value
public class RatingResponse extends Response{
    private long ranking;
}
