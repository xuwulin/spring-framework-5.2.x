package com.xwl.debug.cycle;

/**
 * @author xwl
 * @createdTime 2021/12/16 15:19
 * @description
 */
public class B {
	private A a;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
}
