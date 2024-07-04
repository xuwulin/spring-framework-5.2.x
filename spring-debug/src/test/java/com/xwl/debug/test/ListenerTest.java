package com.xwl.debug.test;

import com.xwl.debug.config.ListenerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author xwl
 * @createdTime 2022/1/7 15:57
 * @description
 */
@SpringJUnitConfig(locations = "classpath:beans.xml")
public class ListenerTest {
	@Test
	public void testListenerConfig() {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(ListenerConfig.class);
		// 手动发布事件
//		ioc.publishEvent(new ApplicationEvent(new String("我发布的事件")) {
//		});
		ioc.close();
	}
}
