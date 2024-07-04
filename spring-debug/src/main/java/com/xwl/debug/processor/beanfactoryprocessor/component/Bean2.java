package com.xwl.debug.processor.beanfactoryprocessor.component;

import org.springframework.stereotype.Component;

@Component
public class Bean2 {

    public Bean2() {
		System.out.println("我被 Spring 管理啦");
    }
}
