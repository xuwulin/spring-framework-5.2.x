package com.xwl.debug.applicationcontext;

import com.xwl.debug.bean.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

/**
 * @author xwl
 * @since 2022/4/6 22:15
 */
public class TestApplicationContext {
	public static void main(String[] args) {
		testClassPathXmlApplicationContext();
//        testFileSystemXmlApplicationContext();
//        testAnnotationConfigApplicationContext();

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		System.out.println("读取之前...");
		for (String name : beanFactory.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		System.out.println("读取之后...");
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(new FileSystemResource("G:\\workspace_idea\\source-code\\spring-framework-source-code\\spring-framework-5.2.x\\spring-debug\\src\\main\\resources\\beans.xml"));
		for (String name : beanFactory.getBeanDefinitionNames()) {
			System.out.println(name);
		}

        /*
            学到了什么
                a. 常见的 ApplicationContext 容器实现
                b. 内嵌容器、DispatcherServlet 的创建方法、作用
         */
	}

	// ⬇️较为经典的容器, 基于 classpath 下 xml 格式的配置文件来创建
	private static void testClassPathXmlApplicationContext() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		System.out.println(context.getBean(Person.class));
	}

	// ⬇️基于磁盘路径下 xml 格式的配置文件来创建
	private static void testFileSystemXmlApplicationContext() {
		// 绝对路径：G:\workspace_idea\source-code\spring-framework-source-code\spring-framework-5.2.x\spring-debug\src\main\resources\beans.xml
//		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src\\main\\resources\\beans.xml");
		// src\main\resources\beans.xml是相对路径
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("G:\\workspace_idea\\source-code\\spring-framework-source-code\\spring-framework-5.2.x\\spring-debug\\src\\main\\resources\\beans.xml");
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		System.out.println(context.getBean(Person.class));
	}

	// ⬇️较为经典的容器, 基于 java 配置类来创建
	private static void testAnnotationConfigApplicationContext() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		System.out.println(context.getBean(Bean2.class).getBean1());
	}

	@Configuration
	static class Config {
		@Bean
		public Bean1 bean1() {
			return new Bean1();
		}

		@Bean
		public Bean2 bean2(Bean1 bean1) {
			Bean2 bean2 = new Bean2();
			bean2.setBean1(bean1);
			return bean2;
		}
	}

	static class Bean1 {
	}

	static class Bean2 {

		private Bean1 bean1;

		public void setBean1(Bean1 bean1) {
			this.bean1 = bean1;
		}

		public Bean1 getBean1() {
			return bean1;
		}
	}
}
