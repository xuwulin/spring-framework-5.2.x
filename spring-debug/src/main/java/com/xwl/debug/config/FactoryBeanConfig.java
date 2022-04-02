package com.xwl.debug.config;

import com.xwl.debug.factorybean.MyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xwl
 * @createdTime 2021/12/30 11:12
 * @description 使用Spring提供的 FactoryBean（工厂Bean）;
 * 1）、默认获取到的是工厂bean调用getObject创建的对象
 * 2）、要获取工厂Bean本身，我们需要给beanName前面加一个&，比如&colorFactoryBean
 */
@Configuration
public class FactoryBeanConfig {
	@Bean
	public MyFactoryBean myFactoryBean() {
		return new MyFactoryBean();
	}
}
