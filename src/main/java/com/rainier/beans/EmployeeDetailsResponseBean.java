package com.rainier.beans;

public class EmployeeDetailsResponseBean {
	private String message;
	private boolean status;
	public Object privilegesList;
	private Object employeeList;

	
	public Object getPrivilegesList() {
		return privilegesList;
	}
	public void setPrivilegesList(Object privilegesList) {
		this.privilegesList = privilegesList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Object getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(Object employeeList) {
		this.employeeList = employeeList;
	}
	

}
