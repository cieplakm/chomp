package com.mmc.chomp.app.game.application.command;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mmc.chomp.app.game.application.command.impl.LeaveGameCommand;
import com.mmc.chomp.app.game.application.command.impl.MoveCommand;
import com.mmc.chomp.app.game.application.command.impl.StartGameCommand;
import com.mmc.chomp.app.game.application.command.impl.WantToPlayCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "requestType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MoveCommand.class, name = "MOVE"),
        @JsonSubTypes.Type(value = StartGameCommand.class, name = "START"),
        @JsonSubTypes.Type(value = LeaveGameCommand.class, name = "LEAVE"),
        @JsonSubTypes.Type(value = WantToPlayCommand.class, name = "WANT_TO_PLAY")
})
public abstract class Command implements Serializable {
    private String requestType;
    private String userId;

    public abstract void execute();
}
