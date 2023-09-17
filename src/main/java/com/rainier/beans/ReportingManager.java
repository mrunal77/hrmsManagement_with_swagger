package com.rainier.beans;

public class ReportingManager {

	private boolean status;
	private String message;
	private Integer employeeUserId;
	private Integer reporting_managerId;
	private String reportingManager;
	private String employeeId;
	private String avilableCasualLeaves;
	private String avilableSickLeaves;
	

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

	public Integer getEmployeeUserId() {
		return employeeUserId;
	}

	public void setEmployeeUserId(Integer employeeUserId) {
		this.employeeUserId = employeeUserId;
	}


	public Integer getReporting_managerId() {
		return reporting_managerId;
	}

	public void setReporting_managerId(Integer reporting_managerId) {
		this.reporting_managerId = reporting_managerId;
	}

	
	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
