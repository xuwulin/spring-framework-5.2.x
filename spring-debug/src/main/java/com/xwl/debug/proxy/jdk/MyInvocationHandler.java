package com.xwl.debug.proxy.jdk;

import java.lang.reflect.Method;

/**
 * 自定义InvocationHandler
 *
 * @author xwl
 * @since 2022/4/11 19:25
 */
public interface MyInvocationHandler {
	/**
	 * 代理类的逻辑写在此方法的实现中
	 *
	 * @param proxy  代理类本身
	 * @param method 正在执行的方法
	 * @param args   正在执行的方法的实际参数
	 * @return
	 * @throws Throwable
	 */
	Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
