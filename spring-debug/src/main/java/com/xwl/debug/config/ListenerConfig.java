package com.xwl.debug.config;

import com.xwl.debug.listener.MyApplicationListener;
import com.xwl.debug.listener.UserServiceListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xwl
 * @createdTime 2022/1/7 16:54
 * @description
 */
@Configuration
public class ListenerConfig {
	@Bean
	public MyApplicationListener myApplicationListener() {
		return new MyApplicationListener();
	}

	@Bean
	public UserServiceListener userServiceListener() {
		return new UserServiceListener();
	}
}
