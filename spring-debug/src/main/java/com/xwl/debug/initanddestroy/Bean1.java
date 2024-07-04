package com.xwl.debug.initanddestroy;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class Bean1 implements InitializingBean {

	/**
	 * 执行顺序：@PostConstruct ->afterPropertiesSet（InitializingBean接口） -> @Bean(initMethod = "init3")
	 * 销毁方法执行顺序也是一样：
	 */
    @PostConstruct
    public void init1() {
        System.out.println("初始化1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化2");
    }

    public void init3() {
        System.out.println("初始化3");
    }
}
