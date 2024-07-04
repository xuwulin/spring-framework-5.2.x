package com.xwl.debug.proxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理demo：只能针对接口代理
 *
 * @author xwl
 * @since 2022/4/7 22:01
 */
public class JdkProxyDemo {

	interface Foo {
		void foo(String param);
	}

	/**
	 * jdk动态代理的目标对象，可以是final类型的，和代理类是兄弟关系
	 * cglib动态代理的目标对象不能是final类型的，和代理类是父子关系
	 */
	static final class Target implements Foo {
		@Override
		public void foo(String param) {
			System.out.println("target foo, param: " + param);
		}
	}

	/**
	 * jdk 只能针对接口代理
	 * jdk动态代理类 和目标对象之间的关系：是兄弟关系，因为他和目标对象同样都实现了同一个接口（Foo）
	 * Proxy.newProxyInstance，内部使用的是ASM动态生成代理类的字节码，源码看不到代理类对于的java代码
	 *
	 * @param param
	 * @throws IOException
	 */
	public static void main(String[] param) throws IOException {
		// 目标对象
		Target target = new Target();
		// 代理类和普通类有个差别，普通类是有java源代码，将源代码编译成字节码，经过类加载就可以使用
		// 而代理类没有源代码，是在运行期间直接生成代理类的字节码，生成的字节码也需要被累加器加载才能使用，
		// 因此需要一个类加载器用来加载在运行期间动态生成的字节码
		ClassLoader loader = JdkProxyDemo.class.getClassLoader();
		// 创建代理对象
		// 第一个参数是类加载器，
		// 第二个参数表示将要生成的代理类要实现哪些接口，数组，表示可以实现多个接口
		// 第三个参数是InvocationHandler，是对实现接口中方法的行为进行封装
		Foo proxy = (Foo) Proxy.newProxyInstance(loader, new Class<?>[]{Foo.class}, new InvocationHandler() {
			/**
			 * 当调用代理的foo方法时，下面的proxy.foo();
			 * 就会执行当前这个invoke方法
			 *
			 * @param proxy   代理对象本身：{$Proxy0@509} com.xwl.debug.proxy.JdkProxyDemo$Target@2f410acf
			 * @param method  正在执行的方法: {Method@510} public abstract void com.xwl.debug.proxy.JdkProxyDemo$Foo.foo()
			 * @param args    正在执行的方法的实际参数
			 * @return
			 * @throws Throwable
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// 相当于前置增强
				System.out.println("before...");

				// 方法正常调用：目标.方法(参数)
				// 方法反射调用：方法.invoke(目标对象, 参数);注意：前16次都是采用反射调用，第17次开始就会针对一个方法生成一个代理类，内部就被优化为正常调用了，无需再反射调用了
				Object result = method.invoke(target, args);

				// 相当于后置增强
				System.out.println("after....");
				// 让代理也返回目标方法执行的结果
				return result;
			}
		});
		System.out.println(proxy.getClass());
		// 调用代理的foo方法
		proxy.foo("hello");
	}
}