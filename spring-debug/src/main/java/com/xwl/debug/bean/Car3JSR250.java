package com.xwl.debug.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author xwl
 * @createdTime 2021/12/30 11:28
 * @description 使用JSR250：
 * 	@PostConstruct：在bean创建完成并且属性赋值完成，来执行初始化方法
 * 	@PreDestroy：在容器销毁bean之前通知我们进行清理工作
 */
public class Car3JSR250 {
	public Car3JSR250() {
		System.out.println("car3 constructor...");
	}

	/**
	 * 在bean创建完成并且属性赋值完成后调用此方法
	 */
	@PostConstruct
	public void init() {
		System.out.println("car3 init（@PostConstruct）...");
	}

	/**
	 * 在容器销毁bean之前通知我们进行清理工作
	 */
	@PreDestroy
	public void destroy() {
		System.out.println("car3 destroy（@PreDestroy）...");
	}
}
