package com.rainier.beans;

import java.sql.Date;

public class LeaveRequestBean {
	private int id;
	private int userId;
	private int leaveTypeId;
	private String leaveType;
	private String leaveStatus;
	private Integer reportingManagerId;
	private String reportingManager;
	private Integer hrId;
	private int appliedLeavesCount;
	private float availableLeaves;
	private String reason;
	private Date fromDate;
	private Date toDate;
	private String leaveFor;
    private int days;
	private String avilableCasualLeaves;
	private String avilableSickLeaves;
	private String email;
	private String name;
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public Integer getReportingManagerId() {
		return reportingManagerId;
	}

	public void setReportingManagerId(Integer reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public Integer getHrId() {
		return hrId;
	}

	public void setHrId(Integer hrId) {
		this.hrId = hrId;
	}

	public int getAppliedLeavesCount() {
		return appliedLeavesCount;
	}

	public void setAppliedLeavesCount(int appliedLeavesCount) {
		this.appliedLeavesCount = appliedLeavesCount;
	}

	

	public float getAvailableLeaves() {
		return availableLeaves;
	}

	public void setAvailableLeaves(float availableLeaves) {
		this.availableLeaves = availableLeaves;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getLeaveFor() {
		return leaveFor;
	}

	public void setLeaveFor(String leaveFor) {
		this.leaveFor = leaveFor;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
	this.days = days;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvilableCasualLeaves() {
		return avilableCasualLeaves;
	}

	public void setAvilableCasualLeaves(String avilableCasualLeaves) {
		this.avilableCasualLeaves = avilableCasualLeaves;
	}

	public String getAvilableSickLeaves() {
		return avilableSickLeaves;
	}

	public void setAvilableSickLeaves(String avilableSickLeaves) {
		this.avilableSickLeaves = avilableSickLeaves;
	}
	

}
