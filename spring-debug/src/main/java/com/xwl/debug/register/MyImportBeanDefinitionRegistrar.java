package com.xwl.debug.register;

import com.xwl.debug.bean.Green;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author xwl
 * @createdTime 2021/12/30 10:43
 * @description
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	/**
	 * 把所有需要添加到IOC容器中的bean调用BeanDefinitionRegistrar.registerBeanDefinitions手动注册进IOC容器
	 *
	 * @param importingClassMetadata 导入类的注解元数据
	 * @param registry               当前 bean 定义注册表
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		boolean red = registry.containsBeanDefinition("com.xwl.debug.bean.Red");
		boolean blue = registry.containsBeanDefinition("com.xwl.debug.bean.Blue");
		if (red && blue) {
			// 指定Bean的定义信息（Bean的类型，Bean的作用域scope等）
			BeanDefinition beanDefinition = new RootBeanDefinition(Green.class);
			// 手动向IOC容器注册Bean
			registry.registerBeanDefinition("green", beanDefinition);
		}
	}
}
