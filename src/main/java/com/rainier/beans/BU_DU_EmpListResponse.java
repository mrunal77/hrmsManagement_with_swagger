package com.rainier.beans;

public class BU_DU_EmpListResponse {
	private String message;
	private  boolean status;
	private Object BUDUEmpList;
	public Object privilegesList;
	
	
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
	public Object getBUDUEmpList() {
		return BUDUEmpList;
	}
	public void setBUDUEmpList(Object bUDUEmpList) {
		BUDUEmpList = bUDUEmpList;
	}

	
	
}
