package com.xwl.debug.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xwl
 * @since 2022/4/6 22:50
 */
public class TestLifeCycle {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(LifeCycleBean.class);
		// 调用close方法时会销毁容器，调用destroy方法
		applicationContext.close();
	}
}
