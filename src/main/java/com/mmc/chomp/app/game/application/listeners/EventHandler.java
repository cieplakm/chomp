package com.mmc.chomp.app.game.application.listeners;

public interface EventHandler<T> {

    void handle(T event);

}
