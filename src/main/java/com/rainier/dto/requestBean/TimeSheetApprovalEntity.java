package com.rainier.dto.requestBean;

public class TimeSheetApprovalEntity 
{
	private int userId;
	private String status;	
	private String reason;
	private int approverRoleId;
	private String approverRole;
	private short calWeek;
	private int year;
	private short month;
	
	
	public short getMonth() {
		return month;
	}
	public void setMonth(short month) {
		this.month = month;
	}
	public short getCalWeek() {
		return calWeek;
	}
	public void setCalWeek(short calWeek) {
		this.calWeek = calWeek;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getApproverRoleId() {
		return approverRoleId;
	}
	public void setApproverRoleId(int approverRoleId) {
		this.approverRoleId = approverRoleId;
	}
	public String getApproverRole() {
		return approverRole;
	}
	public void setApproverRole(String approverRole) {
		this.approverRole = approverRole;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	

}
