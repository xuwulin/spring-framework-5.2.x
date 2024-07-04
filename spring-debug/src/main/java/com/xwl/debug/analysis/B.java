package com.xwl.debug.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {
	@Autowired
	private A a;
}
