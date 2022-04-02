package com.xwl.debug.config;

import com.xwl.debug.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xwl
 * @createdTime 2021/12/30 14:59
 * @description Bean属性赋值
 * @PropertySource(value = {"classpath:person.properties"})：使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量
 */
@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class BeanPropertyValuesConfig {
	@Bean
	public Person person() {
		return new Person();
	}
}
