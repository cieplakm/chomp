package com.mmc.chomp.app.system.infrastructure.events.impl.handlers;

import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SpringEventHandler implements EventHandler {

    private final Class<?> eventType;

    private final Method method;

    private final String beanName;

    private final BeanFactory factory;

    public SpringEventHandler(Class<?> eventType, Method method, String beanName, BeanFactory factory) {
        this.eventType = eventType;
        this.method = method;
        this.beanName = beanName;
        this.factory = factory;
    }

    @Override
    public boolean canHandle(Object eventClazz) {
        return eventType.isAssignableFrom(eventClazz.getClass());
    }

    @Override
    public void handle(Object event) {
        Object bean = factory.getBean(beanName);

        try {
            method.invoke(bean, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
