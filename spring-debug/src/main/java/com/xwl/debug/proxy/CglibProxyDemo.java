package com.xwl.debug.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理demo：可以针对接口和类
 *
 * @author xwl
 * @since 2022/4/7 22:01
 */
public class CglibProxyDemo {

	/**
	 * cglib动态代理的目标对象，不能是final类型（final类型没有子类）的，和代理类是父子关系（目标对象作为父类），目标对象可以是接口也可以是类
	 * jdk动态代理的目标对象可以是final类型的，和代理类是兄弟关系
	 */
	static class Target {
		/**
		 * 方法也不能是final类型的，因为代理类是子类，需要通过方法重写来获得增强
		 */
		public void foo(String param) {
			System.out.println("target foo, param: " + param);
		}
	}

	/**
	 * 代理是子类型, 目标是父类型
	 *
	 * @param param
	 */
	public static void main(String[] param) {
		// 目标对象（父类型）
        Target target = new Target();

		// Enhancer.create()方法返回的是代理对象
		// 代理对象作为子类，可以强制转换为父类
		Target proxy = (Target) Enhancer.create(Target.class, new MethodInterceptor() {
			/**
			 * 当调用代理的foo方法时，下面的proxy.foo();
			 * 就会执行当前这个intercept方法
			 *
			 * @param proxy 代理对象本身
			 * @param method 正在执行的方法
			 * @param args 正在执行的方法的实际参数
			 * @param methodProxy 方法代理，它可以避免反射调用方法
			 * @return
			 * @throws Throwable
			 */
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				// 相当于前置增强
				System.out.println("before...");

				// 调用方法，相当于是调用重写（子类重写父类方法）后的方法
				// 方法反射调用：方法.invoke(目标对象, 参数);
//				Object result = method.invoke(target, args);
				// methodProxy 它可以避免反射调用
				// 内部没有用反射, 需要目标对象（spring就是用的这种）
//				Object result = methodProxy.invoke(target, args);
				// 内部没有用反射, 需要代理对象本身
				Object result = methodProxy.invokeSuper(proxy, args);

				// 相当于后置增强
				System.out.println("after...");
				return result;
			}
		});
		System.out.println(proxy.getClass());
		// 调用代理的foo方法
		proxy.foo("hello");
	}
}