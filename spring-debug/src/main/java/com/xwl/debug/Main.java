package com.xwl.debug;


import com.xwl.debug.bean.Person;
import com.xwl.debug.config.annotation.BeanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description
 */
public class Main {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
		// xml配置文件方式
		/*ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
		Person person = (Person) ioc.getBean("person");
		System.out.println(person);
		Teacher teacher = (Teacher) ioc.getBean("teacher");
		System.out.println(teacher);*/

		// 注解方式
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
		Person person = (Person) applicationContext.getBean("person");
		System.out.println(person);
		System.out.println(person.sayHello());

		// 注意：classpath:META-INF/spring.factories 只是到当前类路径下查找，在jar包中是找不到的
		// classpath*:META-INF/spring.factories这样就能查找jar包中的
		Resource[] resources = applicationContext.getResources("classpath*:META-INF/spring.factories");
		for (Resource resource : resources) {
			System.out.println(resource);
		}

		System.out.println(applicationContext.getEnvironment().getProperty("JAVA_HOME"));
		System.out.println(applicationContext.getEnvironment().getProperty("server.port"));
	}
}
