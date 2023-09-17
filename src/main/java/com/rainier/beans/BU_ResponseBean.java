package com.rainier.beans;

public class BU_ResponseBean {
	private String message;
	private boolean status;
	private int id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
