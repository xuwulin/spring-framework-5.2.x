package com.xwl.debug.config.annotation;

import com.xwl.debug.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author xwl
 * @createdTime 2021/12/29 16:17
 * @description @Scope注解使用
 */
@Configuration
public class ScopeConfig {
	/**
	 * @Bean 给容器众注册一个bean，默认是单实例的bean，类型为方法的返回值类型，beanName默认为方法名，也可以自定义beanName
	 *
	 * @Scope 调整作用域
	 * 	singleton：单实例的（默认值）：ioc容器启动会调用方法创建对象放到IOC容器中，以后每次获取就是直接从容器中拿。
	 * 	prototype：多实例的：ioc容器启动并不会去调用方法创建对象放到IOC容器中，每次获取的时候才会调用方法创建对象。
	 * 	request：同一次请求创建一个实例
	 * 	session：同一个session创建一个实例
	 *
	 * @Lazy 懒加载：
	 * 	单实例bean：默认在容器启动的时候创建对象；
	 * 	懒加载：容器启动不创建对象。第一次使用（获取）Bean创建对象，并初始化；
	 *
	 * @return
	 */
	@Scope(value = "prototype")
	@Bean
	public Person person() {
		System.out.println("给IOC容器中添加Person");
		return new Person("1", "张三", "张三疯");
	}
}
