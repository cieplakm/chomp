package com.mmc.chomp.communication.response;

import com.mmc.chomp.communication.CommandType;

public abstract class Response {
    private CommandType type;
    private String userId;

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
