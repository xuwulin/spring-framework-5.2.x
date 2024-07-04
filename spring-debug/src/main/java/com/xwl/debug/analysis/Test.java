package com.xwl.debug.analysis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		UserService userService = (UserService) applicationContext.getBean("userService");

//		userService.test();
//		userService.insert();

		A a = (A) applicationContext.getBean("a");
		a.test();
		System.out.println(a);
	}
}
