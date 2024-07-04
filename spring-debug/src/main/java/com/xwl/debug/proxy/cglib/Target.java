package com.xwl.debug.proxy.cglib;

/**
 * cglib动态代理目标对象
 *
 * @author xwl
 * @since 2022/4/7 22:01
 */
public class Target {
	public void save() {
		System.out.println("save()");
	}

	public void save(int i) {
		System.out.println("save(int)");
	}

	public void save(long j) {
		System.out.println("save(long)");
	}
}
