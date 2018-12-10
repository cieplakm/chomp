package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.TurnChangedEvent;
import org.springframework.stereotype.Component;

@Component
public class TurnChangingListener implements EventHandler<TurnChangedEvent> {


    public void handle(TurnChangedEvent event){
        //todo: notify user about his turn
    }
}
