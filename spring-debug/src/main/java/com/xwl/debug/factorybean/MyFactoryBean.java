package com.xwl.debug.factorybean;

import com.xwl.debug.bean.Color;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author xwl
 * @createdTime 2021/12/30 10:58
 * @description Spring定义的FactoryBean
 * 要获取工厂Bean本身，我们需要给id前面加一个&，比如&myFactoryBean
 */
public class MyFactoryBean implements FactoryBean<Color> {
	/**
	 *
	 * @return 返回一个Color对象，该对象会加入到IOC容器中
	 * @throws Exception
	 */
	@Override
	public Color getObject() throws Exception {
		System.out.println("sout-->MyFactoryBean#getObject()");
		return new Color();
	}

	/**
	 * 返回对象的类型
	 * @return
	 */
	@Override
	public Class<?> getObjectType() {
		return Color.class;
	}

	/**
	 * 该对象是否是单例
	 * @return true：单例，容器中只会有一个该Bean；false：多实例，每次获取都会创建一个新的Bean
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
}
