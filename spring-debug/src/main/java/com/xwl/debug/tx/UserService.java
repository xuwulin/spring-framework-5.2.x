package com.xwl.debug.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public void insertUser() {
		userDao.insert();
		System.out.println("插入完成...");
//		int i = 10 / 0;
	}

}
