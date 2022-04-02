package com.xwl.debug.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author xwl
 * @createdTime 2021/12/30 11:28
 * @description 通过让Bean实现InitializingBean（定义初始化逻辑）；DisposableBean（定义销毁逻辑）;
 */
public class Car2InitializingBeanAndDisposableBean implements InitializingBean, DisposableBean {
	public Car2InitializingBeanAndDisposableBean() {
		System.out.println("car2 constructor...");
	}

	/**
	 * 初始化方法，在Bean创建完成，并且属性赋值完成后调用
	 *
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("car2 init（afterPropertiesSet）...");
	}

	/**
	 * 销毁方法
	 *
	 * @throws Exception
	 */
	@Override
	public void destroy() throws Exception {
		System.out.println("car2 destroy...");
	}
}
