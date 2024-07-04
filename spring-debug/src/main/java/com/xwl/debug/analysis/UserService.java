package com.xwl.debug.analysis;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService /*implements InitializingBean*/ {
//	private User user;

//	@Autowired
	private OrderService orderService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserService userService;

	public UserService() {
		System.out.println("调用无参构造");
	}

//	@Autowired
	public UserService(OrderService os) {
		this.orderService = os;
		System.out.println("调用有参构造");
	}


//	public void test() {
//		System.out.println(orderService);
//	}
//
//	@PostConstruct
//	public void setUser() {
//		System.out.println("查询数据库给 user 进行赋值操作");
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		System.out.println("初始化操作");
//	}

	@Transactional
	public void insert() {
		jdbcTemplate.execute("INSERT INTO `account` (id, username, balance) VALUES(2, 'zs', 50)");
//		insert2();// 等价于this.insert2(); 由普通对象调用方法
//		userService.insert2();
		UserService us = (UserService) AopContext.currentProxy();
		us.insert2();

//		throw new RuntimeException("模拟异常");
	}

	@Transactional(propagation = Propagation.NEVER)
	public void insert2() {
		jdbcTemplate.execute("INSERT INTO `account` (id, username, balance) VALUES(3, 'ls', 60)");
	}
}
