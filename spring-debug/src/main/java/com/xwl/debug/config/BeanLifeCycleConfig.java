package com.xwl.debug.config;

import com.xwl.debug.bean.Car;
import com.xwl.debug.bean.Car2InitializingBeanAndDisposableBean;
import com.xwl.debug.bean.Car3JSR250;
import com.xwl.debug.processor.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xwl
 * @createdTime 2021/12/30 11:20
 * @description bean的生命周期：Bean创建--实例化--初始化--销毁
 * IOC容器管理Bean的生命周期：我们可以自定义初始化方法和销毁方法，容器在Bean进行到当前生命周期的时候来调用我们自定义的初始化方法和销毁方法
 *
 * 构造（对象创建）
 * 	单实例：在容器启动的时候创建对象
 * 	多实例：在每次获取的时候创建对象\
 *
 * BeanPostProcessor.postProcessBeforeInitialization
 * 初始化：对象创建完成，并赋值好，调用初始化方法。。。
 * BeanPostProcessor.postProcessAfterInitialization
 *
 * 销毁：
 * 	单实例：容器关闭的时候
 * 	多实例：容器不会管理这个bean；容器不会调用销毁方法；
 *
 * BeanPostProcessor原理：
 * 遍历得到容器中所有的BeanPostProcessor；挨个执行beforeInitialization，
 * 但是一但返回null，跳出for循环，不会执行后面的BeanPostProcessor.postProcessorsBeforeInitialization
 *
 * populateBean(beanName, mbd, instanceWrapper); // 给bean进行属性赋值
 * initializeBean
 * {
 * 	applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * 	invokeInitMethods(beanName, wrappedBean, mbd); // 执行自定义初始化
 * 	applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 *
 * Spring提供的控制Bean生命周期的功能：
 * 1）、指定初始化和销毁方法：
 * 	xml配置：init-method和destroy-method
 * 	注解方式：通过@Bean指定init-method和destroy-method；
 * 2）、通过让Bean实现InitializingBean（定义初始化逻辑）；DisposableBean（定义销毁逻辑）;
 * 3）、可以使用JSR250：
 * 	@PostConstruct：在bean创建完成并且属性赋值完成，来执行初始化方法
 * 	@PreDestroy：在容器销毁bean之前通知我们进行清理工作
 * 4）、BeanPostProcessor【interface】：Bean的后置处理器；
 * 	在bean初始化前后进行一些处理工作：
 * 		postProcessBeforeInitialization：在初始化方法之前调用该方法
 * 		postProcessAfterInitialization：在初始化方法之后调用该方法
 *
 * Spring底层对 BeanPostProcessor 的使用：bean赋值，注入其他组件，@Autowired，生命周期注解功能，@Async,xxx BeanPostProcessor;
 *
 */
@Configuration
public class BeanLifeCycleConfig {
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public Car car() {
		return new Car();
	}

	@Bean
	public Car2InitializingBeanAndDisposableBean car2() {
		return new Car2InitializingBeanAndDisposableBean();
	}

	@Bean
	public Car3JSR250 car3() {
		return new Car3JSR250();
	}

	@Bean
	public MyBeanPostProcessor myBeanPostProcessor() {
		return new MyBeanPostProcessor();
	}
}
