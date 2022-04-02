package com.xwl.debug.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description BeanFactoryPostProcessor【interface】：beanFactory的后置处理器；
 * 在BeanFactory标准初始化之后，可以修改应用程序上下文（IOC容器中）的内部bean工厂。
 * 所有bean定义都将被加载到beanFactory，但尚未实例化任何bean。这甚至允许覆盖或添加属性，甚至是急切初始化的bean。
 *
 * BeanFactoryPostProcessor原理:
 * 1)、ioc容器创建对象refresh();方法
 * 2)、invokeBeanFactoryPostProcessors(beanFactory);
 *  如何找到所有的BeanFactoryPostProcessor并执行他们的方法；
 *  	1）、直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
 *   	2）、在初始化创建其他组件前面执行
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		int count = beanFactory.getBeanDefinitionCount();
		String[] names = beanFactory.getBeanDefinitionNames();
		System.out.println("MyBeanFactoryPostProcessor#postProcessBeanFactory()方法执行==>当前BeanFactory中有" + count + " 个Bean");
		System.out.println(Arrays.asList(names));
	}
}
