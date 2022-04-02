package com.xwl.debug.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description
 */
@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert() {
		String sql = "INSERT INTO `tbl_user` (username, age) VALUES(?, ?)";
		String username = UUID.randomUUID().toString().substring(0, 5);
		jdbcTemplate.update(sql, username, 19);
	}

}
