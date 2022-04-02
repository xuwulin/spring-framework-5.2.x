package com.xwl.debug.test;

import com.xwl.debug.aop.AOPConfig;
import com.xwl.debug.aop.MathCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author xwl
 * @createdTime 2021/12/31 16:08
 * @description
 */
@SpringJUnitConfig(locations = "classpath:beans.xml")
public class AOPTest {
	@Test
	public void testAOPConfig() {
		ApplicationContext ioc = new AnnotationConfigApplicationContext(AOPConfig.class);
		MathCalculator bean = ioc.getBean(MathCalculator.class);
		System.out.println(bean);
		int div = bean.div(1, 1);
		System.out.println(div);
	}
}
