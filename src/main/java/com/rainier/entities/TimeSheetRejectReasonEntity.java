package com.rainier.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TimeSheetRejectReasonEntity")
public class TimeSheetRejectReasonEntity
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	 private int id;

	@Column(name = "timesheet_id")
	 private int timeSheetId;

	
	@Column(name = "reason")
	 private String reason;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getTimeSheetId() {
		return timeSheetId;
	}


	public void setTimeSheetId(int timeSheetId) {
		this.timeSheetId = timeSheetId;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}

}
