package com.dv.webmanager.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextAwareImpl implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextAwareImpl.applicationContext = applicationContext;
    }
    
    @SuppressWarnings("unchecked")
    public static <T>
    T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

}
