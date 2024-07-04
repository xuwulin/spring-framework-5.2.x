package com.xwl.debug.processor.beanfactoryprocessor;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

/**
 * Mapper后置处理器：注解@Mapper的自定义实现
 * （mapper接口如何被spring管理）
 * 其实spring并不能真正管理接口，最终还是管理的对象，接口如何变成对象从而被spring管理的呢？
 * 如mybatis，就有一个MapperFactoryBean接口，通过这个接口生产出mapper对象，我们使用的就是这个工厂生产的mapper对象，而不是mapper接口
 *
 * @author xwl
 * @since 2022/4/7 22:01
 */
public class MapperPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException {
		try {
			// 通配符解析器，扫描class资源
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			// TODO 实际开发不应该直接写死 (Config.class)
			// /** 表示包含mapper包下的所有子包
			Resource[] resources = resolver.getResources("classpath:com/xwl/debug/processor/beanfactoryprocessor/mapper/**/*.class");
			CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
			for (Resource resource : resources) {
				MetadataReader reader = factory.getMetadataReader(resource);
				ClassMetadata classMetadata = reader.getClassMetadata();
				// 判断是否是接口，是接口的就留下
				if (classMetadata.isInterface()) {
					// 参照以下代码设置BeanDefinition：
					/*public MapperFactoryBean<Mapper1> mapper1(SqlSessionFactory sqlSessionFactory) {
						// 创建工厂对象，针对Mapper1接口，生产mapper1对象
						MapperFactoryBean<Mapper1> factory = new MapperFactoryBean<>(Mapper1.class);
						factory.setSqlSessionFactory(sqlSessionFactory);
						return factory;
					}*/
					AbstractBeanDefinition bd = BeanDefinitionBuilder
							// 限定BeanDefinition的类型是MapperFactoryBean（Mapper工厂）
							.genericBeanDefinition(MapperFactoryBean.class)
							// 设置构造方法的参数值：接口类型（接口类名，如：Mapper1.class）作为构造方法参数
							.addConstructorArgValue(classMetadata.getClassName())
							// 设置自动装配：按照类型装配（sqlSessionFactory）
							.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
							.getBeanDefinition();
					// 名字生成器
					AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
					// bd2的作用仅仅是为了生成的beanName有区分（Spring的源码就是这样作的），他并不会被添加到容器中
					// 根据接口来生成的，接口名就是classMetadata.getClassName()，每个接口的名字不一样
					AbstractBeanDefinition bd2 = BeanDefinitionBuilder
							.genericBeanDefinition(classMetadata.getClassName())
							.getBeanDefinition();
					// 获取beanName
					String name = generator.generateBeanName(bd2, beanFactory);
					// 最终向容器中注册的是mapper工厂：MapperFactoryBean
					beanFactory.registerBeanDefinition(name, bd);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}
}
