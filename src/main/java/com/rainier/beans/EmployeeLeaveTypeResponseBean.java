package com.rainier.beans;

public class EmployeeLeaveTypeResponseBean {
	private String messsage;
	private boolean status;
	private Object leaveTypelist;

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Object getLeaveTypelist() {
		return leaveTypelist;
	}

	public void setLeaveTypelist(Object leaveTypelist) {
		this.leaveTypelist = leaveTypelist;
	}

}
