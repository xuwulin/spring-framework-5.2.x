package com.xwl.debug.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xwl
 * @createdTime 2021/12/30 16:11
 * @description @Autowired可以标注的位置：构造器，参数，方法，属性；如果是自定义类型的值，都是从容器中获取参数组件的值
 * 1）、【标注在方法位置】：@Bean+方法参数；参数从IOC容器中获取；默认不写@Autowired效果是一样的，都能自动装配
 * 2）、【标在构造器上】：如果组件只有一个有参构造器，参数从IOC容器中获取；这个有参构造器的@Autowired可以省略，
 * 3）、【放在参数位置】：参数位置的组件还是可以自动从IOC容器中获取
 *
 * 默认加载到IOC容器中的组件，容器启动会调用无参构造函数创建对象，再进行初始化赋值等操作
 */
@Component
public class Company {
	private Employee employee;

//	public Company() {
//	}

	/**
	 * @Autowired 标注在有参构造函数上，构造函数使用的参数，如果是自定义类型的值，会从IOC容器中获取
	 * @param employee
	 */
	@Autowired
	public Company(Employee employee) {
		this.employee = employee;
		System.out.println("Company 有参构造函数");
	}

	/**
	 * @Autowired 标注在参数位置，如果是自定义类型的值，参数位置的组件还是可以自动从IOC容器中获取
	 * @param employee
	 */
//	public Company(@Autowired Employee employee) {
//		this.employee = employee;
//		System.out.println("Company 有参构造函数");
//	}

	/**
	 * @Autowired 如果组件只有一个有参构造器，参数从IOC容器中获取；这个有参构造器的@Autowired可以省略，
	 * @param employee
	 */
//	public Company(Employee employee) {
//		this.employee = employee;
//		System.out.println("Company 有参构造函数");
//	}

	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @Autowired 标注在方法上，Spring创建当前对象，就会调用该方法完成属性赋值。方法使用的参数，如果是自定义类型的值，会从IOC容器中获取
	 * @param employee
	 */
//	@Autowired
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Company{" +
				"employee=" + employee +
				'}';
	}
}
