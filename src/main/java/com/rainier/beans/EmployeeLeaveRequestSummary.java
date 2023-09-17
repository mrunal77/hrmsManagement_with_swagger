package com.rainier.beans;

import java.sql.Date;

public class EmployeeLeaveRequestSummary {
	private int id;
	private int leaveRequestId;
	private int userId;
	private String userName;
	private int departmentId;
	private String departmentName;
	private int businessUnitId;
	private String businessUnitName;
	private String reason;
	private String approverComments;
	private int leaveTypeId;
	private String leaveTypeName;
	private int leaveDay;
	private Date fromDate;
	private Date toDate;
//	public enum leaveStatus{pendingForApproval,Approved,Rejected,Cancel;}
	private int reportingManagerId;
	private String reportingManagerName;
	private int hrId;
	private String hrName;
	private float noOfDays;
	private float appliedLeavesCount;
	private int isSatHoliday;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLeaveRequestId() {
		return leaveRequestId;
	}
	public void setLeaveRequestId(int leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getBusinessUnitId() {
		return businessUnitId;
	}
	public void setBusinessUnitId(int businessUnitId) {
		this.businessUnitId = businessUnitId;
	}
	public String getBusinessUnitName() {
		return businessUnitName;
	}
	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	public int getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
	public int getLeaveDay() {
		return leaveDay;
	}
	public void setLeaveDay(int leaveDay) {
		this.leaveDay = leaveDay;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getReportingManagerId() {
		return reportingManagerId;
	}
	public void setReportingManagerId(int reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}
	public String getReportingManagerName() {
		return reportingManagerName;
	}
	public void setReportingManagerName(String reportingManagerName) {
		this.reportingManagerName = reportingManagerName;
	}
	public int getHrId() {
		return hrId;
	}
	public void setHrId(int hrId) {
		this.hrId = hrId;
	}
	public String getHrName() {
		return hrName;
	}
	public void setHrName(String hrName) {
		this.hrName = hrName;
	}
	public float getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(float noOfDays) {
		this.noOfDays = noOfDays;
	}
	public float getAppliedLeavesCount() {
		return appliedLeavesCount;
	}
	public void setAppliedLeavesCount(float appliedLeavesCount) {
		this.appliedLeavesCount = appliedLeavesCount;
	}
	public int getIsSatHoliday() {
		return isSatHoliday;
	}
	public void setIsSatHoliday(int isSatHoliday) {
		this.isSatHoliday = isSatHoliday;
	}
	
	
	

}
