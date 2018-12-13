package com.mmc.chomp.app.game.application.command;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mmc.chomp.app.game.application.command.impl.CreateGameCommand;
import com.mmc.chomp.app.game.application.command.impl.Join2GameCommand;
import com.mmc.chomp.app.game.application.command.impl.MoveCommand;
import com.mmc.chomp.app.game.application.command.impl.StartGameCommand;
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
        @JsonSubTypes.Type(value = CreateGameCommand.class, name = "CREATE"),
        @JsonSubTypes.Type(value = Join2GameCommand.class, name = "JOIN"),
        @JsonSubTypes.Type(value = MoveCommand.class, name = "MOVE"),
        @JsonSubTypes.Type(value = StartGameCommand.class, name = "START")
})
public abstract class Command implements Serializable {
    private String requestType;
    private String userId;

    public abstract void execute();
}
