package com.mmc.chomp.communication.response;

import com.mmc.chomp.app.canonicalmodel.publishedlanguage.AggregateId;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class StateResponse extends Response {
    private String status;
    private boolean[][] board;
    private AggregateId creatorId;
    private AggregateId joinerId;
    private AggregateId winnerId;
}
