package com.rainier.beans;

import java.sql.Date;

public class HolidayNamesBean {
	private int id;
	private String holidayNames;
	private Date holidayDate;
	private int holidayYear;
	private String holidayDescription;
	private int isactive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHolidayNames() {
		return holidayNames;
	}

	public void setHolidayNames(String holidayNames) {
		this.holidayNames = holidayNames;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public int getHolidayYear() {
		return holidayYear;
	}

	public void setHolidayYear(int holidayYear) {
		this.holidayYear = holidayYear;
	}

	public String getHolidayDescription() {
		return holidayDescription;
	}

	public void setHolidayDescription(String holidayDescription) {
		this.holidayDescription = holidayDescription;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

}
