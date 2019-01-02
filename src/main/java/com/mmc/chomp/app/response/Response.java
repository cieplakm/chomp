package com.mmc.chomp.app.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GameCreatedResponse.class, name = "GAME_CREATED"),
        @JsonSubTypes.Type(value = GameOverResponse.class, name = "GAME_OVER"),
        @JsonSubTypes.Type(value = GameStartedResponse.class, name = "GAME_STARTED"),
        @JsonSubTypes.Type(value = MoveResponse.class, name = "MOVE"),
        @JsonSubTypes.Type(value = PlayerJoinedResponse.class, name = "PLAYER_JOINED"),
        @JsonSubTypes.Type(value = PlayerLeftResponse.class, name = "PLAYER_LEFT")
})
public abstract class Response {
    private String type;
    private String gameId;

    public Response(String type, String gameId) {
        this.type = type;
        this.gameId = gameId;
    }

    public Response(){

    }

    public String getType() {
        return type;
    }

    public String getGameId() {
        return gameId;
    }

    void setType(String type) {
        this.type = type;
    }

    void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
