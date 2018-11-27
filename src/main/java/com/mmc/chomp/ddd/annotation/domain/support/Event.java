package com.mmc.chomp.ddd.annotation.domain.support;

import lombok.Data;

@Data
public class Event {
    protected EmbeddedId embeddedId;
    private String message;
}
