package com.mmc.chomp.ddd.annotation.domain.support;

import lombok.Value;

@Value
public class ParticipantData {
    private AggregateId aggregateId;
    private String login;
}
