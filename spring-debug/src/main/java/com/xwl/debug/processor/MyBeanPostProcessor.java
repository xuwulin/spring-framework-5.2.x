package com.xwl.debug.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author xwl
 * @createdTime 2021/12/30 14:26
 * @description BeanPostProcessor【interface】：Bean的后置处理器；
 * 在bean初始化（注意：是初始化而不是实例化）前后进行一些处理工作：
 * 	postProcessBeforeInitialization：在bean初始化方法之前调用该方法
 * 	postProcessAfterInitialization：在bean初始化方法之后调用该方法
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
	/**
	 * 在初始化方法之前调用该方法
	 *
	 * @param bean     当前bean实例
	 * @param beanName 当前beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyBeanPostProcessor#postProcessBeforeInitialization()方法执行==>" + beanName + "=>" + bean);
		return bean;
	}

	/**
	 * 在初始化方法之后调用该方法
	 *
	 * @param bean     当前bean实例
	 * @param beanName 当前beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyBeanPostProcessor#postProcessAfterInitialization()方法执行==>" + beanName + "=>" + bean);
		return bean;
	}
}
