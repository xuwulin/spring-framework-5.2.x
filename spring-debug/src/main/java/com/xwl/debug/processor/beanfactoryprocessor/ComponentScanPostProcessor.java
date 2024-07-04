package com.xwl.debug.processor.beanfactoryprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ComponentScan后置处理器：注解@ComponentScan的自定义实现
 *
 * @author xwl
 * @since 2022/4/7 22:01
 */
public class ComponentScanPostProcessor implements BeanDefinitionRegistryPostProcessor {
	/**
	 * 执行context.refresh时执行此方法
	 *
	 * @param configurableListableBeanFactory
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException {
		try {
			// TODO 实际开发不应该直接写死 (Config.class)
			ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
			if (componentScan != null) {
				for (String p : componentScan.basePackages()) {
					System.out.println(p);
					// /** 表示component包下可能含有子包
					// com.itheima.a05.component -> classpath*:com/xwl/debug/processor/beanfactoryprocessor/component/**/*.class
					String path = "classpath*:" + p.replace(".", "/") + "/**/*.class";
					System.out.println(path);
					CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
					// Resource[] resources = context.getResources(path);
					// 也可使用new PathMatchingResourcePatternResolver().getResources(path);效果和context.getResources(path);一样
					Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
					AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
					for (Resource resource : resources) {
						// System.out.println(resource);
						MetadataReader reader = factory.getMetadataReader(resource);
						// System.out.println("类名:" + reader.getClassMetadata().getClassName());
						AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
						// System.out.println("是否加了 @Component:" + annotationMetadata.hasAnnotation(Component.class.getName()));
						// System.out.println("是否加了 @Component的派生注解（@Controller、@Service等）:" + annotationMetadata.hasMetaAnnotation(Component.class.getName()));
						if (annotationMetadata.hasAnnotation(Component.class.getName())
								|| annotationMetadata.hasMetaAnnotation(Component.class.getName())) {
							AbstractBeanDefinition bd = BeanDefinitionBuilder
									.genericBeanDefinition(reader.getClassMetadata().getClassName())
									.getBeanDefinition();
							String name = generator.generateBeanName(bd, beanFactory);
							beanFactory.registerBeanDefinition(name, bd);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
