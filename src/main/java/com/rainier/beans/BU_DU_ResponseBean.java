package com.rainier.beans;

public class BU_DU_ResponseBean {
	private String message;
	private  boolean status;
	private Object BUDUList;
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
	public Object getBUDUList() {
		return BUDUList;
	}
	public void setBUDUList(Object bUDUList) {
		BUDUList = bUDUList;
	}
	

}
