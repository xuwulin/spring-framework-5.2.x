package com.xwl.debug.test;

import com.xwl.debug.tx.TxConfig;
import com.xwl.debug.tx.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author xwl
 * @createdTime 2022/1/7 16:04
 * @description
 */
@SpringJUnitConfig(locations = "classpath:beans.xml")
public class TxTest {
	@Test
	public void test01(){
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(TxConfig.class);
		UserService userService = ioc.getBean(UserService.class);
		userService.insertUser();
		ioc.close();
	}
}
