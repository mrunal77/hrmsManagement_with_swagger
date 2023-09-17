package com.rainier.beans;

public class TimeZoneRequestBean {

	private int id;
	private String timeZone;
	private String timeZoneAbbr;
	private String offsetValue;
	private int isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZoneAbbr() {
		return timeZoneAbbr;
	}

	public void setTimeZoneAbbr(String timeZoneAbbr) {
		this.timeZoneAbbr = timeZoneAbbr;
	}

	public String getOffsetValue() {
		return offsetValue;
	}

	public void setOffsetValue(String offsetValue) {
		this.offsetValue = offsetValue;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

}
