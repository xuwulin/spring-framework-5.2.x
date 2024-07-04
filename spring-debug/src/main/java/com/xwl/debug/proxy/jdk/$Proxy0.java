package com.xwl.debug.proxy.jdk;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 模拟JDK动态代理的实现（自定义代理类）
 * JDK的代理类和目标都要实现共同的接口（代理类和目标是兄弟关系）
 *
 * @author xwl
 * @since 2022/4/7 22:01
 */
public class $Proxy0 extends Proxy implements Foo, Serializable {
	private static final long serialVersionUID = -8522207549330293255L;

	/**
	 * JDK动态代理是通过MyInvocationHandler来实现回调
	 */
//	private MyInvocationHandler h;

	/**
	 * 构造方法
	 * 在创建代理的时候可以把MyInvocationHandler的具体实现传进来
	 * 即：使用匿名内部类
	 * Foo proxy = new $Proxy0(new MyInvocationHandler() {...});
	 * @param h
	 */
//	public $Proxy0(MyInvocationHandler h) {
//		this.h = h;
//	}

	/**
	 * 构造方法（使用JDK定义的InvocationHandler）
	 * 在创建代理的时候可以把InvocationHandler的具体实现传进来
	 * 即：使用匿名内部类
	 * Foo proxy = new $Proxy0(new InvocationHandler() {...});
	 * @param h
	 */
	public $Proxy0(InvocationHandler h) {
		// 把具体的实现赋值给成员变量
		super(h);
	}

	static Method foo;
	static Method bar;

	static {
		try {
			// 获取方法对象
			foo = Foo.class.getMethod("foo");
			bar = Foo.class.getMethod("bar");
		} catch (NoSuchMethodException e) {
			// 静态代码块中的异常必须处理，检查异常需要转换为运行时异常
			throw new NoSuchMethodError(e.getMessage());
		}
	}

	@Override
	public void foo() {
		try {
			h.invoke(this, foo, new Object[0]);
		} catch (RuntimeException | Error e) {
			// 运行异常：直接抛出
			throw e;
		} catch (Throwable e) {
			// 检查时异常：不能直接抛出，需要转换成运行时异常抛出
			throw new UndeclaredThrowableException(e);
		}
	}

	@Override
	public int bar() {
		try {
			Object result = h.invoke(this, bar, new Object[0]);
			return (int) result;
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new UndeclaredThrowableException(e);
		}
	}
}
