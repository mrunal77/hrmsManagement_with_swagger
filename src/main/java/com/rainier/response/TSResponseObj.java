package com.rainier.response;

import java.util.List;

public class TSResponseObj 
{
	boolean status;
	String message;	
	
	List<TSResponseEmployeeName> timeSheetListForRM;
	Object timeSheetCountResponse=null;

	
	public Object getTimeSheetCountResponse() {
		return timeSheetCountResponse;
	}
	public void setTimeSheetCountResponse(Object timeSheetCountResponse) {
		this.timeSheetCountResponse = timeSheetCountResponse;
	}
	public List<TSResponseEmployeeName> getTimeSheetListForRM() {
		return timeSheetListForRM;
	}
	public void setTimeSheetListForRM(List<TSResponseEmployeeName> timeSheetListForRM) {
		this.timeSheetListForRM = timeSheetListForRM;
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
