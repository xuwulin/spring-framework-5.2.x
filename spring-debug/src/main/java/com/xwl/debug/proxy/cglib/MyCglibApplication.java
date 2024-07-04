package com.xwl.debug.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCglibApplication {

	public static void main(String[] args) {
		Proxy proxy = new Proxy();
		Target target = new Target();
		proxy.setMethodInterceptor(new MethodInterceptor() {
			/**
			 * 当调用代理的foo方法时，下面的proxy.foo();
			 * 就会执行当前这个intercept方法
			 *
			 * @param p 代理对象本身
			 * @param method 正在执行的方法
			 * @param args 正在执行的方法的实际参数
			 * @param methodProxy 方法代理，它可以避免反射调用方法
			 * @return
			 * @throws Throwable
			 */
			@Override
			public Object intercept(Object p, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				System.out.println("before...");
//                return method.invoke(target, args); // 反射调用
				// FastClass
				// 内部无反射, 结合目标用(Spring采用)
//                return methodProxy.invoke(target, args);
				// 内部无反射, 结合代理用
				return methodProxy.invokeSuper(p, args);
			}
		});

		proxy.save();
		proxy.save(1);
		proxy.save(2L);
	}
}
