package com.xwl.debug;


import com.xwl.debug.bean.Person;
import com.xwl.debug.config.annotation.BeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description
 */
public class Main {
	public static void main(String[] args) {
		// xml配置文件方式
		/*ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
		Person person = (Person) ioc.getBean("person");
		System.out.println(person);
		Teacher teacher = (Teacher) ioc.getBean("teacher");
		System.out.println(teacher);*/

		// 注解方式
		ApplicationContext ioc = new AnnotationConfigApplicationContext(BeanConfig.class);
		Person person = (Person) ioc.getBean("person");
		System.out.println(person);
		System.out.println(person.sayHello());
	}
}
