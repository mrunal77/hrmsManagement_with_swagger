package com.rainier.beans;

public class Main_businessunitsBean {
	public String message;
	public boolean status;
	public Object privilegesList;
	public Object businessList;		
	
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
	public Object getBusinessList() {
		return businessList;
	}
	public void setBusinessList(Object businessList) {
		this.businessList = businessList;
	}
	
	public Object getPrivilegesList() {
		return privilegesList;
	}
	public void setPrivilegesList(Object privilegesList) {
		this.privilegesList = privilegesList;
	}
	

}
