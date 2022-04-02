package com.xwl.debug.config;

import com.xwl.debug.aware.MyAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xwl
 * @createdTime 2021/12/30 16:54
 * @description
 */
@Configuration
public class AwareConfig {
	@Bean
	public MyAwareImpl myAwareImpl() {
		return new MyAwareImpl();
	}
}
