package com.rainier.dto.responseBean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.rainier.entities.BenchSalesAddCandidateEntity;

public class BenchSalesVendorDetailsEntityResponse 
{


	private int vendorId;
	private String vendorName;
	private String emailAddress;
	private String phoneNo;
	private String clientName;
	private String location;
	private String rate;	
	private Date dateOfSubmission;
	private int createdBy;	
	private String status;	
	private Date statusSubDate;
	private String comments;
	private String payType;
	
	private String recruiterName;

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	private Set<BenchSalesAddCandidateEntity> candidates;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusSubDate() {
		return statusSubDate;
	}

	public void setStatusSubDate(Date statusSubDate) {
		this.statusSubDate = statusSubDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BenchSalesVendorDetailsEntityResponse() {
		candidates = new HashSet<>();
	}

	public Set<BenchSalesAddCandidateEntity> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<BenchSalesAddCandidateEntity> candidates) {
		this.candidates = candidates;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Date getDateOfSubmission() {
		return dateOfSubmission;
	}

	public void setDateOfSubmission(Date dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}


}
