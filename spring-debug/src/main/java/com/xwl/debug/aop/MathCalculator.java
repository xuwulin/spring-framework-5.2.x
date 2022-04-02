package com.xwl.debug.aop;

/**
 * @author xwl
 * @createdTime 2021/12/31 15:59
 * @description
 */
public class MathCalculator {
	public int div(int i, int j) {
		System.out.println("MathCalculator...div...");
		return i / j;
	}
}
