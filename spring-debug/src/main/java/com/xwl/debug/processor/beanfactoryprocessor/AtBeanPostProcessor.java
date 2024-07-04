package com.xwl.debug.processor.beanfactoryprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Set;

/**
 * >@Bean后置处理器：注解@Bean的自定义实现
 *
 * @author xwl
 * @since 2022/4/7 22:01
 */
public class AtBeanPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException {
		try {
			CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
			// 获取Config类信息，
			// TODO 实际开发不应该直接写死 (Config.class)
			MetadataReader reader = factory.getMetadataReader(new ClassPathResource("com/xwl/debug/processor/beanfactoryprocessor/Config.class"));
			Set<MethodMetadata> methods = reader
					// 获取注解元数据
					.getAnnotationMetadata()
					// 获取被注解（这里是@Bean注解）标注的方法
					.getAnnotatedMethods(Bean.class.getName());
			for (MethodMetadata method : methods) {
				System.out.println(method);
				// 获取初始化方法的名字
				String initMethod = method
						// 获取注解属性，返回的是Map集合
						.getAnnotationAttributes(Bean.class.getName())
						// 获取初始化方法上的值（即或者@Bean(initMethod = "init")中initMethod的值）
						.get("initMethod").toString();
				BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
				// 相当于说明工厂方法是谁：参数：方法的名字，工厂对象（对应的beanName）；先得又工厂（config）才能调用工厂中的方法
				builder.setFactoryMethodOnBean(method.getMethodName(), "config");
				// 设置自动装配模式，对于 “构造方法” 和 “工厂方法” 来说选择AUTOWIRE_CONSTRUCTOR
				builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
				if (initMethod.length() > 0) {
					// 如果@Bean注解中的initMethod的属性值不为空，则设置初始化方法名字
					builder.setInitMethodName(initMethod);
				}
				// 获取bean定义信息BeanDefinition
				AbstractBeanDefinition bd = builder.getBeanDefinition();
				// 加入bean工厂，参数：bean的名字（通常使用方法名），bean的定义信息
				beanFactory.registerBeanDefinition(method.getMethodName(), bd);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
