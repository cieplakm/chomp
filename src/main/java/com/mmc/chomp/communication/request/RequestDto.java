package com.mmc.chomp.communication.request;

import com.mmc.chomp.communication.CommandType;

public abstract class RequestDto {

    private CommandType requestType;
    private String userId;

    public CommandType getRequestType() {
        return requestType;
    }

    public String getUserId() {
        return userId;
    }

    public void setRequestType(CommandType requestType) {
        this.requestType = requestType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
