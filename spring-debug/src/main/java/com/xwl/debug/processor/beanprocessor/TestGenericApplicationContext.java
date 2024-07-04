package com.xwl.debug.processor.beanprocessor;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * bean后置处理器的作用
 *
 * @author xwl
 * @since 2022/4/7 22:01
 */
public class TestGenericApplicationContext {
	public static void main(String[] args) {
		// ⬇️GenericApplicationContext 是一个【干净】的容器
		GenericApplicationContext context = new GenericApplicationContext();

		// ⬇️用原始方法注册三个 bean
		context.registerBean("bean1", Bean1.class);
		context.registerBean("bean2", Bean2.class);
		context.registerBean("bean3", Bean3.class);
//		context.registerBean("bean4", Bean4.class);

		context.getDefaultListableBeanFactory()
				// 设置自动装配候选者的解析器，默认的解析器不能解析 @Value值注入，使用ContextAnnotationAutowireCandidateResolver就可以通过@Value进行值注入
				.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
		// 注册bean的后置处理器
		// 解析：@Autowired、@Value注解的bean后置处理器
		// 在依赖注入阶段，解析@Autowired、@Value
		context.registerBean(AutowiredAnnotationBeanPostProcessor.class);

		// 解析：@Resource、@PostConstruct、@PreDestroy注解的bean后置处理器
		// 依赖注入阶段解析@Resource注解，初始化“前”解析@PostConstruct注解，销毁前解析@PreDestroy注解
		context.registerBean(CommonAnnotationBeanPostProcessor.class);
		// 此时会发现，@Resource 优先于 @Autowired，因为当有多个bean后置处理器的时候，会进行排序，CommonAnnotationBeanPostProcessor排前面，所以会先执行CommonAnnotationBeanPostProcessor

		// springboot中的bean后置处理器
		// 在初始化前解析@ConfigurationProperties注解：SpringBoot提供的
//		ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());

		// ⬇️初始化容器
		// 执行beanFactory后处理器, 添加bean后处理器, 初始化所有单例
		context.refresh();

		System.out.println(context.getBean(Bean1.class));

		// ⬇️销毁容器
		context.close();

        /*
            学到了什么
                a. @Autowired 等注解的解析属于 bean 生命周期阶段(依赖注入, 初始化)的扩展功能
                b. 这些扩展功能由 bean 后处理器来完成
         */
	}
}
