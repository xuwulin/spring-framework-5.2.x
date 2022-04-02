package com.xwl.debug.bean;

/**
 * @author xwl
 * @createdTime 2021/12/30 16:37
 * @description
 */
public class Department {

	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Department{" +
				"employee=" + employee +
				'}';
	}
}
