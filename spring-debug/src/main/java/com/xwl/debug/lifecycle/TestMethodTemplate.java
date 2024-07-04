package com.xwl.debug.lifecycle;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板方法模式
 *
 * @author xwl
 * @since 2022/4/6 23:09
 */
public class TestMethodTemplate {
	public static void main(String[] args) {
		MyBeanFactory beanFactory = new MyBeanFactory();
		beanFactory.addBeanPostProcessor(bean -> System.out.println("解析 @Autowired"));
		beanFactory.addBeanPostProcessor(bean -> System.out.println("解析 @Resource"));
		beanFactory.getBean();
	}

	// 模板方法  Template Method Pattern
	static class MyBeanFactory {
		public Object getBean() {
			Object bean = new Object();
			System.out.println("构造 " + bean);
			System.out.println("依赖注入 " + bean); // @Autowired, @Resource
			for (BeanPostProcessor processor : processors) {
				processor.inject(bean);
			}
			System.out.println("初始化 " + bean);
			return bean;
		}

		private List<BeanPostProcessor> processors = new ArrayList<>();

		public void addBeanPostProcessor(BeanPostProcessor processor) {
			processors.add(processor);
		}
	}

	/**
	 * 将代码中会变的部分抽象成接口，便于扩展
	 * 模板方法设计模式：有静有动，静就是固定不变的代码，动就是会变化的代码，把他抽取成接口
	 */
	static interface BeanPostProcessor {
		// 期望功能：对依赖注入阶段的扩展
		void inject(Object bean);
	}
}
