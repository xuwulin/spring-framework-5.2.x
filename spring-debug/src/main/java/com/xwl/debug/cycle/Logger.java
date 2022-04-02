package com.xwl.debug.cycle;

import org.aspectj.lang.JoinPoint;

/**
 * @author xwl
 * @createdTime 2021/12/16 20:23
 * @description
 */
public class Logger {

	public void recordBefore(JoinPoint joinPoint) throws Throwable {
		System.out.println("执行before方法");
	}

	public void recordAfter(JoinPoint joinPoint) throws Throwable {
		System.out.println("执行after方法");
	}
}
