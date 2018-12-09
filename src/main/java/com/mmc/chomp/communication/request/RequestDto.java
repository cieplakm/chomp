package com.mmc.chomp.communication.request;

import com.mmc.chomp.communication.CommandType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto implements Serializable {

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
