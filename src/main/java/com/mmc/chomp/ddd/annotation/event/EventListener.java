package com.mmc.chomp.ddd.annotation.event;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Component
@Target(ElementType.TYPE)
public @interface EventListener {
}
