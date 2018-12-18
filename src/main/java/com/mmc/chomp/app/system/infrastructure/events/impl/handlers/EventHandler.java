package com.mmc.chomp.app.system.infrastructure.events.impl.handlers;

public interface EventHandler {

    boolean canHandle(Object eventClazz);

    void handle(Object event);

}
