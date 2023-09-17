package com.rainier.dto.responseBean;

import java.util.Date;


public class VendorSalesExecutiveResponseBean {
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
	    private String payType;
	    private String salExeName;
		public int getVendorId() {
			return vendorId;
		}
		public void setVendorId(int vendorId) {
			this.vendorId = vendorId;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public int getStatus1() {
			return status1;
		}
		public void setStatus1(int status1) {
			this.status1 = status1;
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
		public String getSalExeName() {
			return salExeName;
		}
		public void setSalExeName(String salExeName) {
			this.salExeName = salExeName;
		}
	    

}
