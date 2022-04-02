package com.xwl.debug.config.annotation;

import com.xwl.debug.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author xwl
 * @createdTime 2021/12/29 16:17
 * @description @Lazy注解使用
 */
@Configuration
public class LazyConfig {
	/**
	 * @Bean 给容器众注册一个bean，默认是单实例的bean，类型为方法的返回值类型，beanName默认为方法名，也可以自定义beanName
	 *
	 * @Lazy 懒加载：
	 * 	单实例bean：默认在容器启动的时候创建对象；
	 * 	懒加载：容器启动不创建对象。第一次使用（获取）Bean创建对象，并初始化；
	 *
	 * @return
	 */
	@Lazy
	@Bean
	public Person person() {
		System.out.println("懒加载--给IOC容器中添加Person");
		return new Person("1", "张三", "张三疯");
	}
}
