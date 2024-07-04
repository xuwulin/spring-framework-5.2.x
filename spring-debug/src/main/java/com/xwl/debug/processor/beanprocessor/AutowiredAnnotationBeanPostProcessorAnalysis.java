package com.xwl.debug.processor.beanprocessor;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * AutowiredAnnotationBeanPostProcessor运行分析
 *
 * @author xwl
 * @since 2022/4/7 23:13
 */
public class AutowiredAnnotationBeanPostProcessorAnalysis {
	public static void main(String[] args) throws Throwable {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 因为是使用new Bean2(); 说明这个bean已经是一个成品的bean了，就不会再执行：创建过程、依赖注入、初始化
		beanFactory.registerSingleton("bean2", new Bean2());
		beanFactory.registerSingleton("bean3", new Bean3());
		// 设置自动装配候选者的解析器，默认的解析器不能解析 @Value值注入，使用ContextAnnotationAutowireCandidateResolver就可以通过@Value进行值注入
		beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
		// ${} 的解析器
		beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

		// 1. 查找哪些属性、方法加了 @Autowired, 这称之为 InjectionMetadata
		AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
		processor.setBeanFactory(beanFactory);

		Bean1 bean1 = new Bean1();
        System.out.println(bean1);
		// 执行依赖注入 解析：@Autowired @Value
		// 第一个参数：指定属性的值，null为不指定，在beanFactory中查找，第二个参数：被注入的目标
        processor.postProcessProperties(null, bean1, "bean1");
        System.out.println(bean1);

		// processor.postProcessProperties 方法分析：
        Method findAutowiringMetadata = AutowiredAnnotationBeanPostProcessor.class
				.getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
        findAutowiringMetadata.setAccessible(true);
		// 获取 Bean1 上加了 @Value @Autowired 的成员变量，方法参数信息
        InjectionMetadata metadata = (InjectionMetadata) findAutowiringMetadata.invoke(processor, "bean1", Bean1.class, null);
        System.out.println(metadata);

		// 2. 调用 InjectionMetadata 来进行依赖注入, 注入时按类型查找值
        metadata.inject(bean1, "bean1", null);
        System.out.println(bean1);

		// 3. 如何按类型查找值
		Field bean3 = Bean1.class.getDeclaredField("bean3");
		DependencyDescriptor dd1 = new DependencyDescriptor(bean3, false);
		// 根据成员变量去找要注入谁
		Object o = beanFactory.doResolveDependency(dd1, null, null, null);
		System.out.println(o);

		Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", Bean2.class);
		DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(setBean2, 0), true);
		// 根据成员变量去找要注入谁
		Object o1 = beanFactory.doResolveDependency(dd2, null, null, null);
		System.out.println(o1);

		Method setHome = Bean1.class.getDeclaredMethod("setHome", String.class);
		DependencyDescriptor dd3 = new DependencyDescriptor(new MethodParameter(setHome, 0), true);
		Object o2 = beanFactory.doResolveDependency(dd3, null, null, null);
		System.out.println(o2);
	}
}
