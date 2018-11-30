package com.mmc.chomp.app.canonicalmodel.publishedlanguage;

import lombok.Value;

@Value
public class PlayerData {
    private AggregateId aggregateId;
    private String login;
}
