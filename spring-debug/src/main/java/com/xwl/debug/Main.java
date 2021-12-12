package com.xwl.debug;


import com.xwl.debug.bean.Person;
import com.xwl.debug.config.PersonConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(PersonConfig.class);
		Person person = (Person) ioc.getBean("person");
		System.out.println(person.sayHello());
	}
}
