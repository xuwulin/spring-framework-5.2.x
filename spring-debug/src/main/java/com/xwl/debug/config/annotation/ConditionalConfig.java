package com.xwl.debug.config.annotation;

import com.xwl.debug.bean.Person;
import com.xwl.debug.condition.LinuxCondition;
import com.xwl.debug.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author xwl
 * @createdTime 2021/12/29 17:09
 * @description @Configuration注解使用：类中组件统一设置。满足当前条件，这个类中配置的所有bean注册才能生效。
 * 要求：如果系统是windows，给容器中注册("bill")，如果是linux系统，给容器中注册("linus")
 */
@Configuration
public class ConditionalConfig {
	/**
	 * @Bean 给容器众注册一个bean，默认是单实例的bean，类型为方法的返回值类型，beanName默认为方法名，也可以自定义beanName
	 * @Conditional({Condition}) 按照一定的条件进行判断，满足条件给容器中注册bean。可以作用在类上，也可以作用在方法上
	 * 如果系统是windows，给容器中注册("bill")
	 * @return
	 */
	@Conditional({WindowsCondition.class})
	@Bean("bill")
	public Person bill() {
		return new Person("1", "Bill Gates", "B");
	}

	/**
	 * @Bean 给容器众注册一个bean，默认是单实例的bean，类型为方法的返回值类型，beanName默认为方法名，也可以自定义beanName
	 * @Conditional({Condition}) 按照一定的条件进行判断，满足条件给容器中注册bean。可以作用在类上，也可以作用在方法上
	 * 如果是linux系统，给容器中注册("linus")
	 * @return
	 */
	@Conditional({LinuxCondition.class})
	@Bean("linus")
	public Person linus() {
		return new Person("2", "Linus", "L");
	}
}
