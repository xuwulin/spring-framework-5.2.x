package com.xwl.debug.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * @author xwl
 * @createdTime 2021/12/30 16:48
 * @description 自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory，xxx）；
 * 自定义组件实现xxxAware；在创建对象的时候，会调用接口规定的方法注入相关组件；Aware；
 * 把Spring底层一些组件注入到自定义的Bean中；
 * xxxAware：功能使用xxxProcessor；
 * 	比如：ApplicationContextAware ==> ApplicationContextAwareProcessor；
 */
public class MyAwareImpl implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("传入的IOC容器：" + applicationContext);
		this.applicationContext = applicationContext;
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("当前Bean的名称：" + name);
	}

	/**
	 * 解析占位符
	 * @param resolver
	 */
	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		String stringValue = resolver.resolveStringValue("你好${os.name}，我是#{11 + 1}");
		System.out.println("解析的字符串：" + stringValue);
	}
}
