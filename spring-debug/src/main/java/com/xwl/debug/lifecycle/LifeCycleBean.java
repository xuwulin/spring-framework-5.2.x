package com.xwl.debug.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * bean的生命周期
 * 执行顺序：构造-->依赖注入-->初始化方法-->销毁方法（当是单例bean的时候才执行销毁方法）
 *
 * @author xwl
 * @since 2022/4/6 22:40
 */
@Component
@Import(MyBeanPostProcessor2.class)
public class LifeCycleBean {

	/**
	 * 构造方法
	 */
	public LifeCycleBean() {
		System.out.println("构造 LifeCycleBean");
	}

	/**
	 * 依赖注入方法
	 * 如果参数是(UserService userService)自定义类，则会根据类型UserService去ioc容器种查找并依赖注入
	 * 但是如果参数是 String类型，默认是不会把他当作一个bean来依赖注入的，除非在参数前加上@Value()注解，来作为依赖注入（值注入），从环境变量或配置文件中取值，
	 *
	 * @param home
	 */
	@Autowired
	public void autowire(@Value("${JAVA_HOME}") String home) {
		System.out.println("依赖注入: " + home);
	}

	/**
	 * 初始化方法
	 */
	@PostConstruct
	public void init() {
		System.out.println("初始化 LifeCycleBean");
	}

	/**
	 * 销毁方法
	 */
	@PreDestroy
	public void destroy() {
		System.out.println("销毁 LifeCycleBean");
	}
}
