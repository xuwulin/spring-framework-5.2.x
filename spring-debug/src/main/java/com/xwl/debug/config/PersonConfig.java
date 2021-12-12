package com.xwl.debug.config;

import com.xwl.debug.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description
 */
@Configuration
public class PersonConfig {
	@Bean(name = "person")
	public Person initBean() {
		return new Person();
	}
}
