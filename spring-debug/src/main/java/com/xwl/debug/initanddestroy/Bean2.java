package com.xwl.debug.initanddestroy;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

public class Bean2 implements DisposableBean {

	/**
	 * 执行顺序：@PreDestroy ->destroy（DisposableBean接口） -> @Bean(destroyMethod = "destroy3")
	 */
    @PreDestroy
    public void destroy1() {
        System.out.println("销毁1");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁2");
    }

    public void destroy3() {
        System.out.println("销毁3");
    }
}
