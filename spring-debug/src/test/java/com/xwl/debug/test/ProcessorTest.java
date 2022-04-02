package com.xwl.debug.test;

import com.xwl.debug.config.ProcessorConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author xwl
 * @createdTime 2022/1/7 15:57
 * @description
 */
@SpringJUnitConfig(locations = "classpath:beans.xml")
public class ProcessorTest {
	@Test
	public void testExtConfig() {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(ProcessorConfig.class);
		ioc.close();
	}
}
