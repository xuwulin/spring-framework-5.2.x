package com.xwl.debug.bean;

/**
 * @author xwl
 * @createdTime 2021/12/30 11:28
 * @description
 */
public class Car {
	public Car() {
		System.out.println("car constructor...");
	}

	public void init() {
		System.out.println("car init...");
	}

	public void destroy() {
		System.out.println("car destroy...");
	}
}
