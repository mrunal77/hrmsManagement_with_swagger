package com.rainier.beans;

public class EntityStatusBean {

	private boolean status;
	private String message;
	private int userId;
	public Object privilegesList;
	private int isactive;
	
	
	

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public Object getPrivilegesList() {
		return privilegesList;
	}

	public void setPrivilegesList(Object privilegesList) {
		this.privilegesList = privilegesList;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
