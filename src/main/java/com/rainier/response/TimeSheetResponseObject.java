package com.rainier.response;

import com.rainier.entities.SavingTimeSheet;

import java.util.List;

public class TimeSheetResponseObject 
{
	private String Message;
	private boolean status;
	private int month;
	private int year;
	private int week;
	private String currentWeek;
	private String previousWeek;
	private String nextWeek;
	private List<SavingTimeSheet> timeSheetDetails;
	private String weeklyStatus ;
	public String getWeeklyStatus() 
	{
		return weeklyStatus;
	}

	public void setWeeklyStatus(String weeklyStatus) {
		this.weeklyStatus = weeklyStatus;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getCurrentWeek() {
		return currentWeek;
	}

	public void setCurrentWeek(String currentWeek) {
		this.currentWeek = currentWeek;
	}

	public String getPreviousWeek() {
		return previousWeek;
	}

	public void setPreviousWeek(String previousWeek) {
		this.previousWeek = previousWeek;
	}

	public String getNextWeek() {
		return nextWeek;
	}

	public void setNextWeek(String nextWeek) {
		this.nextWeek = nextWeek;
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

	public List<SavingTimeSheet> getTimeSheetDetails() {
		return timeSheetDetails;
	}

	public void setTimeSheetDetails(List<SavingTimeSheet> timeSheetDetails) {
		this.timeSheetDetails = timeSheetDetails;
	}

}
