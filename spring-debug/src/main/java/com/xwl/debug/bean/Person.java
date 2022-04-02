package com.xwl.debug.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author xwl
 * @createdTime 2021/12/12 19:01
 * @description 使用@Value赋值
 * 1、基本数值
 * 2、可以写SPEL表达式：#{}
 * 3、可以写${}：取出配置文件（properties或者在运行环境变量里面的值）中的值
 *
 */
public class Person {

	@Value("#{20 - 1}")
	private String id;

	@Value("张三")
	private String name;

	@Value("${nickname}")
	private String nickname;

	public Person() {
	}

	public Person(String id, String name, String nickname) {
		this.id = id;
		this.name = name;
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String sayHello() {
		return "super man";
	}

	@Override
	public String toString() {
		return "Person{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", nickname='" + nickname + '\'' +
				'}';
	}
}
