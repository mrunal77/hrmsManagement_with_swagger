package com.rainier.beans;

import java.util.List;

public class TimeSheetResponse {
	
	private String Message;
	private boolean status;

	private List<TimeSheetSummary> monthlyTimeSheetList;
	private List<TimeSheetsMonthlyBean>timeSheetListForRM;
	
	
	
	
	public List<TimeSheetsMonthlyBean> getTimeSheetListForRM() {
		return timeSheetListForRM;
	}

	public void setTimeSheetListForRM(List<TimeSheetsMonthlyBean> timeSheetListForRM) {
		this.timeSheetListForRM = timeSheetListForRM;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<TimeSheetSummary> getMonthlyTimeSheetList() {
		return monthlyTimeSheetList;
	}
	
	public void setMonthlyTimeSheetList(List<TimeSheetSummary> monthlyTimeSheetList) {
		this.monthlyTimeSheetList = monthlyTimeSheetList;
	}
}
