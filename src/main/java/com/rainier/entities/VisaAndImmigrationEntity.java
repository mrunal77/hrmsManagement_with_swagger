package com.rainier.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_empvisadetails")
public class VisaAndImmigrationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "passport_number")
	private String passportNo;

	@Column(name = "passport_issue_date")
	private Timestamp passportIssueDate;

	@Column(name = "passport_expiry_date")
	private Timestamp passportExiperyDate;

	@Column(name = "visa_number")
	private String visaNo;

	@Column(name = "visa_type")
	private String visaType;

	@Column(name = "visa_issue_date")
	private Timestamp visaIssuedDate;

	@Column(name = "visa_expiry_date")
	private Timestamp visaExiperyDate;

	@Column(name = "inine_status")
	private String inineStatus;

	@Column(name = "inine_review_date")
	private Timestamp inineReviewDate;

	@Column(name = "issuing_authority")
	private String issuingAuthority;

	@Column(name = "ininetyfour_status")
	private String ininetyfourStatus;

	@Column(name = "ininetyfour_expiry_date")
	private Timestamp ininetyfourExpiryDate;

	@Column(name = "createddate", updatable = false)
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@Column(name = "isactive")
	private int isactive;

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

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

}
