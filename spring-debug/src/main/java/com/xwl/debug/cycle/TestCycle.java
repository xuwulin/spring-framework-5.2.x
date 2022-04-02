package com.xwl.debug.cycle;

import com.xwl.debug.cycle.A;
import com.xwl.debug.cycle.B;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xwl
 * @createdTime 2021/12/16 15:26
 * @description
 */
public class TestCycle {
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("cycle.xml");
		A a = ioc.getBean(A.class);
		System.out.println(a.getB());
		B b = ioc.getBean(B.class);
		System.out.println(b.getA());
	}
}
