package com.rainier.beans;

import java.sql.Timestamp;

public class MainTimezoneBean {
	
	private int id;
	private int actualId;
	private String timeZone;
	private String timeZoneAbbr;
	private String description;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	private int isActive;
	
	

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActualId() {
		return actualId;
	}

	public void setActualId(int actualId) {
		this.actualId = actualId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
