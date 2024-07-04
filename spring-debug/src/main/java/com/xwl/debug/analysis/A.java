package com.xwl.debug.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {
	@Autowired
//	@Lazy
	private B b;

//	@Async
	public void test() {
		System.out.println(b);
	}
}
