package com.xwl.debug.config.annotation;

import com.xwl.debug.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description @Configuration：告诉Spring这是一个配置类，配置类 == 配置文件
 */
@Configuration
public class BeanConfig {
	/**
	 * @Bean 给容器众注册一个bean，默认是单实例的bean，类型为方法的返回值类型，beanName默认为方法名，也可以自定义beanName
	 * @return
	 */
	@Bean(name = "person")
	public Person initBean() {
		return new Person("1", "张三", "张三疯");
	}
}
