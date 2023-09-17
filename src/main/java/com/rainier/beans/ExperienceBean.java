package com.rainier.beans;

import java.sql.Date;

public class ExperienceBean {
	
	private int id;
	private Integer userId;
	private String companyName;
	private String companyWebsite;
	private String designation;
	private Date fromDate;
	private Date toDate;
	private String reasonForLeaving;
	private String referrerName;
	private String referrerContact;
	private String referrerEmail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
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

	public String getReasonForLeaving() {
		return reasonForLeaving;
	}

	public void setReasonForLeaving(String reasonForLeaving) {
		this.reasonForLeaving = reasonForLeaving;
	}

	public String getReferrerName() {
		return referrerName;
	}

	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}

	public String getReferrerContact() {
		return referrerContact;
	}

	public void setReferrerContact(String referrerContact) {
		this.referrerContact = referrerContact;
	}

	public String getReferrerEmail() {
		return referrerEmail;
	}

	public void setReferrerEmail(String referrerEmail) {
		this.referrerEmail = referrerEmail;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
