package com.rainier.response;

import com.rainier.entities.SavingTimeSheet;

import java.util.List;

public class TimeSheetResponseEmp 
{
	private String Message;
	private boolean status;

	private List<SavingTimeSheet> timeSheetDetails;

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

	public List<SavingTimeSheet> getTimeSheetDetails() {
		return timeSheetDetails;
	}

	public void setTimeSheetDetails(List<SavingTimeSheet> timeSheetDetails) {
		this.timeSheetDetails = timeSheetDetails;
	}

}
