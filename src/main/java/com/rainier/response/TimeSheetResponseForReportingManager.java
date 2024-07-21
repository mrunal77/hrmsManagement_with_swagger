package com.rainier.response;

import com.rainier.beans.TimeSheetSummary;

import java.util.List;

public class TimeSheetResponseForReportingManager 
{
	boolean status;
	String message;	
	int reportingMangerId;
	List<TimeSheetSummary> timeSheetList;

	public int getReportingMangerId() {
		return reportingMangerId;
	}
	public void setReportingMangerId(int reportingMangerId) {
		this.reportingMangerId = reportingMangerId;
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
	public List<TimeSheetSummary> getTimeSheetList() {
		return timeSheetList;
	}
	public void setTimeSheetList(List<TimeSheetSummary> timeSheetList) {
		this.timeSheetList = timeSheetList;
	}

	
	
}
