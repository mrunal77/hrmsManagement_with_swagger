package com.rainier.beans;

public class LeaveManagementOptionsResponseBean {
	private String message;
	private boolean status;
	private Object listOfLeaveManagementOptions;

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

	public Object getListOfLeaveManagementOptions() {
		return listOfLeaveManagementOptions;
	}

	public void setListOfLeaveManagementOptions(Object listOfLeaveManagementOptions) {
		this.listOfLeaveManagementOptions = listOfLeaveManagementOptions;
	}

}
