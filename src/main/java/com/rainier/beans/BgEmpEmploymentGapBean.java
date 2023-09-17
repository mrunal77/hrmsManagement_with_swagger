package com.rainier.beans;

import java.util.Date;

public class BgEmpEmploymentGapBean {
	private int id;
	private int userId;
	private Date fromDate;
	private Date toDate;
	private String reasonForEmployment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getReasonForEmployment() {
		return reasonForEmployment;
	}
	public void setReasonForEmployment(String reasonForEmployment) {
		this.reasonForEmployment = reasonForEmployment;
	}
	
	
	

}
