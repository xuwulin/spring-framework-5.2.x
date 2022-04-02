package com.xwl.debug.config;

import com.xwl.debug.bean.Car;
import com.xwl.debug.processor.MyBeanDefinitionRegistryPostProcessor;
import com.xwl.debug.processor.MyBeanFactoryPostProcessor;
import com.xwl.debug.processor.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xwl
 * @createdTime 2022/1/7 16:52
 * @description
 */
@Configuration
public class ProcessorConfig {
	@Bean
	public MyBeanPostProcessor myBeanPostProcessor() {
		return new MyBeanPostProcessor();
	}

	@Bean
	public MyBeanFactoryPostProcessor myBeanFactoryPostProcessor() {
		return new MyBeanFactoryPostProcessor();
	}

	@Bean
	public MyBeanDefinitionRegistryPostProcessor myBeanDefinitionRegistryPostProcessor() {
		return new MyBeanDefinitionRegistryPostProcessor();
	}

	@Bean
	public Car car() {
		return new Car();
	}
}
