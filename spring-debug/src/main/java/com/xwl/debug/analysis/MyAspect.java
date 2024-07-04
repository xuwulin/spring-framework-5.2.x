package com.xwl.debug.analysis;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Before("execution(public void com.xwl.debug.analysis.UserService.test())")
	public void testBefore(JoinPoint joinPoint) {
		System.out.println("before");
	}
}
