package com.xwl.debug.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 模拟cglib动态代理的实现（自定义代理类）
 * cglib的代理类需要继承/实现目标类/接口（目标和代理类是父子关系）
 *
 * @author xwl
 * @since 2022/4/7 22:01
 */
public class Proxy extends Target {

	/**
	 * cglib动态代理是通过MethodInterceptor来实现回调
	 */
	private MethodInterceptor methodInterceptor;

	public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
		this.methodInterceptor = methodInterceptor;
	}

	static Method save0;
	static Method save1;
	static Method save2;
	static MethodProxy save0Proxy;
	static MethodProxy save1Proxy;
	static MethodProxy save2Proxy;

	static {
		try {
			save0 = Target.class.getMethod("save");
			save1 = Target.class.getMethod("save", int.class);
			save2 = Target.class.getMethod("save", long.class);
			/**
			 * 创建方法代理对象
			 * 第一个参数：目标对象
			 * 第二个参数：代理对象
			 * 第三个参数：()表示无参，(I)表示一个参数为int类型，(J)表示一个参数为long类型，V表示返回值类型是void
			 * 第四个参数：增强方法的名字
			 * 第五个参数：原始方法的名字
			 */
			save0Proxy = MethodProxy.create(Target.class, Proxy.class, "()V", "save", "saveSuper");
			save1Proxy = MethodProxy.create(Target.class, Proxy.class, "(I)V", "save", "saveSuper");
			save2Proxy = MethodProxy.create(Target.class, Proxy.class, "(J)V", "save", "saveSuper");
		} catch (NoSuchMethodException e) {
			throw new NoSuchMethodError(e.getMessage());
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 带原始功能的方法
	public void saveSuper() {
		super.save();
	}

	public void saveSuper(int i) {
		super.save(i);
	}

	public void saveSuper(long j) {
		super.save(j);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 带增强功能的方法
	@Override
	public void save() {
		try {
			/**
			 * 当调用代理的foo方法时，下面的proxy.foo();
			 * 就会执行当前这个intercept方法
			 *
			 * @param proxy 代理对象本身
			 * @param method 正在执行的方法
			 * @param args 正在执行的方法的实际参数
			 * @param methodProxy 它可以避免反射调用方法
			 * @return
			 * @throws Throwable
			 */
			methodInterceptor.intercept(this, save0, new Object[0], save0Proxy);
		} catch (Throwable e) {
			throw new UndeclaredThrowableException(e);
		}
	}

	@Override
	public void save(int i) {
		try {
			methodInterceptor.intercept(this, save1, new Object[]{i}, save1Proxy);
		} catch (Throwable e) {
			throw new UndeclaredThrowableException(e);
		}
	}

	@Override
	public void save(long j) {
		try {
			methodInterceptor.intercept(this, save2, new Object[]{j}, save2Proxy);
		} catch (Throwable e) {
			throw new UndeclaredThrowableException(e);
		}
	}
}
