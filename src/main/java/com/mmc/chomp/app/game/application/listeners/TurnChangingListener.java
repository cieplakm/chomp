package com.mmc.chomp.app.game.application.listeners;

import com.mmc.chomp.app.game.domain.game.events.TurnChangedEvent;
import com.mmc.chomp.ddd.annotation.event.EventListener;
import com.mmc.chomp.ddd.annotation.event.EventSubscriber;

@EventListener
public class TurnChangingListener {

    @EventSubscriber
    public void handle(TurnChangedEvent event){
        //todo: notify user about his turn
    }
}
