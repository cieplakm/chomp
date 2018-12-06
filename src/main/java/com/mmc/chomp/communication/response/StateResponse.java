package com.mmc.chomp.communication.response;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class StateResponse extends Response {
    private String status;
    private boolean[][] board;
    private String creatorId;
    private String joinerId;
    private String winnerId;
}
