package com.rainier.beans;

import java.sql.Timestamp;

public class VisaAndImmigrationBean {
	
	
	private int id;
	private int userId;
	private String passportNo;
	private Timestamp passportIssueDate;
	private Timestamp passportExiperyDate;
	private String visaNo;
	private String visaType;
	private Timestamp visaIssuedDate;
	private Timestamp visaExiperyDate;
	private String inineStatus;
	private Timestamp inineReviewDate;
	private String issuingAuthority;
	private String ininetyfourStatus;
	private Timestamp ininetyfourExpiryDate;
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
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public Timestamp getPassportIssueDate() {
		return passportIssueDate;
	}
	public void setPassportIssueDate(Timestamp passportIssueDate) {
		this.passportIssueDate = passportIssueDate;
	}
	public Timestamp getPassportExiperyDate() {
		return passportExiperyDate;
	}
	public void setPassportExiperyDate(Timestamp passportExiperyDate) {
		this.passportExiperyDate = passportExiperyDate;
	}
	public String getVisaNo() {
		return visaNo;
	}
	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public Timestamp getVisaIssuedDate() {
		return visaIssuedDate;
	}
	public void setVisaIssuedDate(Timestamp visaIssuedDate) {
		this.visaIssuedDate = visaIssuedDate;
	}
	public Timestamp getVisaExiperyDate() {
		return visaExiperyDate;
	}
	public void setVisaExiperyDate(Timestamp visaExiperyDate) {
		this.visaExiperyDate = visaExiperyDate;
	}
	public String getInineStatus() {
		return inineStatus;
	}
	public void setInineStatus(String inineStatus) {
		this.inineStatus = inineStatus;
	}
	public Timestamp getInineReviewDate() {
		return inineReviewDate;
	}
	public void setInineReviewDate(Timestamp inineReviewDate) {
		this.inineReviewDate = inineReviewDate;
	}
	public String getIssuingAuthority() {
		return issuingAuthority;
	}
	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}
	public String getIninetyfourStatus() {
		return ininetyfourStatus;
	}
	public void setIninetyfourStatus(String ininetyfourStatus) {
		this.ininetyfourStatus = ininetyfourStatus;
	}
	public Timestamp getIninetyfourExpiryDate() {
		return ininetyfourExpiryDate;
	}
	public void setIninetyfourExpiryDate(Timestamp ininetyfourExpiryDate) {
		this.ininetyfourExpiryDate = ininetyfourExpiryDate;
	}
	
	
	

}
