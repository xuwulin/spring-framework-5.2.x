package com.xwl.debug.processor.beanfactoryprocessor.component;

import org.springframework.stereotype.Controller;

@Controller
public class Bean3 {

    public Bean3() {
		System.out.println("我被 Spring 管理啦");
    }
}
