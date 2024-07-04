package com.xwl.debug.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 内置功能InitializingBean、ApplicationContextAware
 * 不会失效
 */
@Configuration
public class MyConfig2 implements InitializingBean, ApplicationContextAware {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("注入 ApplicationContext");
    }

    @Bean //  beanFactory 后处理器
    public BeanFactoryPostProcessor processor2() {
        return beanFactory -> {
            System.out.println("执行 processor2");
        };
    }
}
