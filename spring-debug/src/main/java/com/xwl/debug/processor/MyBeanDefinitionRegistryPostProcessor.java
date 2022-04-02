package com.xwl.debug.processor;

import com.xwl.debug.bean.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 * postProcessBeanDefinitionRegistry();
 * 在标准初始化之后修改应用程序上下文的内部bean定义注册表。
 * 所有常规bean定义都将要被加载，但尚未实例化任何bean。这允许在下一个后处理阶段开始之前添加更多的bean定义。
 * 优先于BeanFactoryPostProcessor执行；
 * 可以利用BeanDefinitionRegistryPostProcessor给容器中再额外添加一些组件；
 *
 * 原理：
 *  1）、ioc创建对象refresh();方法
 *  2）、invokeBeanFactoryPostProcessors(beanFactory);
 *  3）、从容器中获取到所有的BeanDefinitionRegistryPostProcessor组件。
 *   		1、依次触发所有的postProcessBeanDefinitionRegistry()方法
 *  		2、再来触发postProcessBeanFactory()方法BeanFactoryPostProcessor；
 *  4）、再来从容器中找到BeanFactoryPostProcessor组件；然后依次触发postProcessBeanFactory()方法
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor#postProcessBeanFactory()方法执行==>bean的数量：" + beanFactory.getBeanDefinitionCount());
	}

	/**
	 * 该方法先于 postProcessBeanFactory() 方法执行
	 * BeanDefinitionRegistry Bean定义信息的保存中心，
	 * 以后BeanFactory就是按照BeanDefinitionRegistry里面保存的每一个bean定义信息创建bean实例；
	 * @param registry the bean definition registry used by the application context
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry()方法执行==>bean的数量：" + registry.getBeanDefinitionCount());
		// 手动给容器中注册组件
//		RootBeanDefinition beanDefinition = new RootBeanDefinition(Blue.class);
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();
		registry.registerBeanDefinition("hello", beanDefinition);
	}
}
