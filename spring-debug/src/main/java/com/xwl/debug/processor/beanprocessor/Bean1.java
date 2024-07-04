package com.xwl.debug.processor.beanprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * bean后置处理器的作用
 *
 * @author xwl
 * @since 2022/4/7 22:01
 */
public class Bean1 {

	private Bean2 bean2;

	/**
	 * 依赖注入
	 *
	 * @param bean2
	 */
	@Autowired
	public void setBean2(Bean2 bean2) {
		System.out.println("@Autowired 生效: " + bean2);
		this.bean2 = bean2;
	}

	@Autowired
	private Bean3 bean3;

	/**
	 * 依赖注入
	 *
	 * @param bean3
	 */
	@Resource
	public void setBean3(Bean3 bean3) {
		System.out.println("@Resource 生效: " + bean3);
		this.bean3 = bean3;
	}

	private String home;

	/**
	 * 值注入
	 *
	 * @param home
	 */
	@Autowired
	public void setHome(@Value("${JAVA_HOME}") String home) {
		System.out.println("@Value 生效: " + home);
		this.home = home;
	}

	/**
	 * 初始化方法
	 */
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct 生效");
	}

	/**
	 * 销毁方法
	 */
	@PreDestroy
	public void destroy() {
		System.out.println("@PreDestroy 生效");
	}

	@Override
	public String toString() {
		return "Bean1{" +
				"bean2=" + bean2 +
				", bean3=" + bean3 +
				", home='" + home + '\'' +
				'}';
	}
}
