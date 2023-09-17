package com.rainier.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BenchSalesVendorDetailsBean {
    private int vendorId;
    private int id;
    private String vendorName;
    private String emailAddress;
    private String phoneNo;
    private int status1;
    private String clientName;
    private String location;
    private String rate;
    private Date dateOfSubmission;
    private int createdBy;
    private String status;
	private Date statusSubDate;
    private String comments;
    private String payRate;
    private String payType;
	private List<Integer> recIdList;
	private int reqId;
	
	
	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
	}
	public int getStatus1() {
		return status1;
	}
	public void setStatus1(int status1) {
		this.status1 = status1;
	}
	public List<Integer> getRecIdList() {
		return recIdList;
	}
	public void setRecIdList(List<Integer> recIdList) {
		this.recIdList = recIdList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	private Set<BenchSalesAddCandidateBean>candidates;
	
	
	public BenchSalesVendorDetailsBean() {
		candidates=new HashSet<>();
	}
	public Set<BenchSalesAddCandidateBean> getCandidates() {
		return candidates;
	}
	public void setCandidates(Set<BenchSalesAddCandidateBean> candidates) {
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
	public String getPayRate() {
		return payRate;
	}
	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
    
}
