package com.rj.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 如果一个类是 ApplicationContextAware的子类
 * 则其setApplicationContext会被系统调用，可以接受到当前的工厂对象
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware{
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }
}
