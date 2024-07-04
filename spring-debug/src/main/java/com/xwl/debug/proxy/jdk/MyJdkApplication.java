package com.xwl.debug.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义InvocationHandler
 *
 * @author xwl
 * @since 2022/4/11 19:25
 */
public class MyJdkApplication {
	/**
	 * 实现接口的目标
	 */
	static class Target implements Foo {
		@Override
		public void foo() {
			System.out.println("target foo");
		}

		@Override
		public int bar() {
			System.out.println("target bar");
			return 100;
		}
	}

	public static void main(String[] param) {
		// 使用自定义MyInvocationHandler
//		Foo proxy = new $Proxy0(new MyInvocationHandler() {
		// 使用JDK的InvocationHandler
		Foo proxy = new $Proxy0(new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// 1. 功能增强
				System.out.println("before...");
				// 2. 调用目标
//                new Target().foo();
				return method.invoke(new Target(), args);
			}
		});
		proxy.foo();
		proxy.bar();
        /*
            学到了什么: 代理一点都不难, 无非就是利用了多态、反射的知识
                1. 方法重写可以增强逻辑, 只不过这【增强逻辑】千变万化, 不能写死在代理内部
                2. 通过接口回调将【增强逻辑】置于代理类之外
                3. 配合接口方法反射(也是多态), 就可以再联动调用目标方法
         */
	}
}