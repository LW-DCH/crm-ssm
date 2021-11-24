package com.qy23.sm.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName SrpingUtils
 * @Author 刘伟
 * @Date 2020/10/22 21:23
 * @Description
 * @Version 1.0
 **/
@Component
public class SpringUtils implements ApplicationContextAware {

    /**
     * 容器启动时会自动执行这个函数
     *
     * @param applicationContext
     * @throws BeansException
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }
}
