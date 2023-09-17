package com.rainier.response;

import java.util.List;

import com.rainier.entities.EmployeeDetailsEntity;

public class EmployeesForReportingManagerResponse  
{
	boolean status;
	String message;	
	int reportingMangerId;
	List<EmployeeDetailsEntity> empDetailsList;
	
	
	public int getReportingMangerId() {
		return reportingMangerId;
	}
	public void setReportingMangerId(int reportingMangerId) {
		this.reportingMangerId = reportingMangerId;
	}
	
	public List<EmployeeDetailsEntity> getEmpDetailsList() {
		return empDetailsList;
	}
	public void setEmpDetailsList(List<EmployeeDetailsEntity> empDetailsList) {
		this.empDetailsList = empDetailsList;
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
	
}
