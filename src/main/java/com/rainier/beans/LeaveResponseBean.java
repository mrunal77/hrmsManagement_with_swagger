package com.rainier.beans;

public class LeaveResponseBean {
	public String message;
	public boolean status;
	public Object addEmployeeLeaveLimit;
	public Object privilegesList;
	
	
	
	public Object getPrivilegesList() {
		return privilegesList;
	}
	public void setPrivilegesList(Object privilegesList) {
		this.privilegesList = privilegesList;
	}
	public Object getAddEmployeeLeaveLimit() {
		return addEmployeeLeaveLimit;
	}
	public void setAddEmployeeLeaveLimit(Object addEmployeeLeaveLimit) {
		this.addEmployeeLeaveLimit = addEmployeeLeaveLimit;
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

	

}
