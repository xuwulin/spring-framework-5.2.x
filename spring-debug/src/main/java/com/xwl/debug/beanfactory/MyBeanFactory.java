package com.xwl.debug.beanfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.annotation.Resource;

/**
 * BeanFactory的实现
 *
 * @author xwl
 * @since 2022/4/5 21:01
 */
public class MyBeanFactory {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// bean 的定义（class, scope, 初始化, 销毁）
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(Config.class)
				.setScope("singleton")
				.getBeanDefinition();
		// 注册beanDefinition
		beanFactory.registerBeanDefinition("config", beanDefinition);

		for (String name : beanFactory.getBeanDefinitionNames()) {
			// 此时只会打印出config，Config类上的@Configuration注解以及@Bean注解并没有解析
			// 即此时的beanFactory还缺少解析注解的功能
			System.out.println(name);
		}

		// 把一些常用的后置处理器添加（这里只是添加，并没有工作）到bean工厂，
		// 这些后置处理器是对beanDefinition功能的扩展，原始的beanDefinition并没有解析注解的功能
		// 比如BeanFactory后置处理器：org.springframework.context.annotation.internalConfigurationAnnotationProcessor，就是解析@Configuration注解以及@Bean注解的
		// 比如Bean后置处理器：org.springframework.context.annotation.internalAutowiredAnnotationProcessor，解析@Autowired注解
		// 比如Bean后置处理器：org.springframework.context.annotation.internalCommonAnnotationProcessor，解析@Resource注解
		AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

		// BeanFactory后置处理器（如internalConfigurationAnnotationProcessor），补充了一些 bean 定义
		beanFactory.getBeansOfType(BeanFactoryPostProcessor.class)
				.values()
				.forEach(beanFactoryPostProcessor -> {
					// 执行BeanFactory后置处理器，即，解析@Configuration、@Bean等注解
					beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
				});

		// Bean后置处理器（如internalAutowiredAnnotationProcessor、internalCommonAnnotationProcessor）,
		// 针对bean的生命周期的各个阶段提供扩展, 例如 @Autowired @Resource ...
		beanFactory.getBeansOfType(BeanPostProcessor.class)
				.values()
				.stream()
				// 当某个成员变量上既加了@Autowired注解，也加了@Resource注解时，@Autowired优先级高，因为他比@Resource先解析，
				// 但是可以通过比较器排序，调整优先级
				.sorted(beanFactory.getDependencyComparator())
				.forEach(beanPostProcessor -> {
					System.out.println("bean的后置处理器：>>>>" + beanPostProcessor);
					// 建立beanFactory与后置处理器之间的联系
					beanFactory.addBeanPostProcessor(beanPostProcessor);
				});

		for (String name : beanFactory.getBeanDefinitionNames()) {
			System.out.println(name);
		}

		// 准备好所有单例，即一开始就准备好单例对象（提前创建好单例对象），默认是在第一次使用bean的时候才创建对象（延迟实例化）
		beanFactory.preInstantiateSingletons();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
//        System.out.println(beanFactory.getBean(Bean1.class).getBean2());
		System.out.println(beanFactory.getBean(Bean1.class).getInter());
        /*
            学到了什么（beanFactory与applicationContext的区别）:
            a. beanFactory 不会做的事
                   1. 不会主动调用 BeanFactory 后处理器
                   2. 不会主动添加 Bean 后处理器
                   3. 不会主动初始化单例
                   4. 不会解析beanFactory 还不会解析 ${ } 与 #{ }
            b. bean 后处理器会有排序的逻辑
         */

		System.out.println("Common:" + (Ordered.LOWEST_PRECEDENCE - 3));
		System.out.println("Autowired:" + (Ordered.LOWEST_PRECEDENCE - 2));
	}

	@Configuration
	static class Config {
		@Bean
		public Bean1 bean1() {
			return new Bean1();
		}

		@Bean
		public Bean2 bean2() {
			return new Bean2();
		}

		@Bean
		public Bean3 bean3() {
			return new Bean3();
		}

		@Bean
		public Bean4 bean4() {
			return new Bean4();
		}
	}

	interface Inter {

	}

	static class Bean3 implements Inter {

	}

	static class Bean4 implements Inter {

	}

	static class Bean1 {

		public Bean1() {
			System.out.println("构造 Bean1()");
		}

		@Autowired
		private Bean2 bean2;

		public Bean2 getBean2() {
			return bean2;
		}

		/**
		 * 这种方式注入不会成功，因为bean3和bean4都是Inter类型，会报错（@Autowired是根据类型去匹配的）
		 * 可以通过修改成员变量的名字：private Inter bean3;先根据类型去找，如果发现有多个，再根据成员变量的名字和bean的名字匹配，如果匹配上了则优先选择
		 */
		/*@Autowired
		private Inter inter;
		public Inter getInter() {
			return inter;
		}*/
		/*@Autowired
		private Inter bean3;
		public Inter getInter() {
			return bean3;
		}*/

		/**
		 * 使用@Resource(name = "bean4")，指定要加载的bean，加了name，就不会根据成员变量的名字去匹配了
		 */
		@Autowired
		@Resource(name = "bean4")
		private Inter bean3;

		public Inter getInter() {
			return bean3;
		}
	}

	static class Bean2 {

		public Bean2() {
			System.out.println("构造 Bean2()");
		}
	}
}
