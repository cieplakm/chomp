package com.mmc.chomp.app.system.infrastructure.events.impl;

import com.mmc.chomp.ddd.annotation.event.EventSubscriber;
import com.mmc.chomp.app.system.infrastructure.events.impl.handlers.EventHandler;
import com.mmc.chomp.app.system.infrastructure.events.impl.handlers.SpringEventHandler;
import com.mmc.chomp.ddd.support.domain.DomainEventPublisher;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class EventListenerFinderRegister implements BeanPostProcessor {

    private DomainEventPublisher eventPublisher;
    private BeanFactory factory;


    @Autowired
    public EventListenerFinderRegister(DomainEventPublisher eventPublisher, BeanFactory factory) {
        this.eventPublisher = eventPublisher;
        this.factory = factory;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        for (Method method : bean.getClass().getMethods()) {
            EventSubscriber subscriber = method.getAnnotation(EventSubscriber.class);

            if (subscriber == null){
                continue;
            }

            Class<?> eventType = method.getParameterTypes()[0];

            EventHandler eventHandler = new SpringEventHandler(eventType, method, beanName, factory);

            eventPublisher.registerHandler(eventHandler);
        }


        return bean;
    }
}
