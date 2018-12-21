package com.mmc.chomp.app.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GameCreatedResponse.class, name = "GAME_CREATED")
})
public abstract class Response {
    private String type;

    public Response(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
