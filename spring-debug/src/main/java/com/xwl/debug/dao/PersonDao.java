package com.xwl.debug.dao;

import org.springframework.stereotype.Repository;

/**
 * @author xwl
 * @createdTime 2021/12/29 11:03
 * @description 使用@Repository注解将PersonDao注入到IOC容器中，beanName默认为类名首字母小写
 */
@Repository
public class PersonDao {
	private String label = "1";

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "PersonDao{" +
				"label='" + label + '\'' +
				'}';
	}
}
