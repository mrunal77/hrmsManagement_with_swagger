package com.rainier.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="rs_bgEmpPersonalInfo")

public class BgEmpPersonalInfoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="id")
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="employeeAliasName")
	private String aliasName;
	
	@Column(name="socialSecurityNumber")
	private String socialSecurityNumber;
	
	@Column(name="curr_state")
	private String currentState;
	
	@Column(name="curr_city")
	private String currentCity;
	
	@Column(name="curr_zipcode")
	private String currentZipcode;
	
	@Column(name="perm_state")
	private String permState;
	
	@Column(name="perm_city")
	private String permCity;
	
	@Column(name="perm_zipcode")
	private String permZipcode;
	
	@Column(name="businessNo")
	private String businessNo;
	
	@Column(name="emailAddress")
	private String emailAddress;
	
	@Column(name="previous_state")
	private String previousState;
	
	@Column(name="previous_city")
	private String previousCity;
	
	@Column(name="previous_zipcode")
	private String previousZipcode;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dateOfBirth")
	private Date dob;
	
	@Column(name="driverLicence_StateId")
	private String driverLicense_StateId;
	
	@Column(name="question1WorkUs")
	private String question1;
	
	@Column(name="question2CrimeCase")
	private String question2;
	
	@Column(name="question2Reason")
	private String question2_Reason;
	
	@Column(name="isSameCurrasPerm")
	private int isSameCurr_as_Perm;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getCurrentZipcode() {
		return currentZipcode;
	}

	public void setCurrentZipcode(String currentZipcode) {
		this.currentZipcode = currentZipcode;
	}

	public String getPermState() {
		return permState;
	}

	public void setPermState(String permState) {
		this.permState = permState;
	}

	public String getPermCity() {
		return permCity;
	}

	public void setPermCity(String permCity) {
		this.permCity = permCity;
	}

	public String getPermZipcode() {
		return permZipcode;
	}

	public void setPermZipcode(String permZipcode) {
		this.permZipcode = permZipcode;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPreviousState() {
		return previousState;
	}

	public void setPreviousState(String previousState) {
		this.previousState = previousState;
	}

	public String getPreviousCity() {
		return previousCity;
	}

	public void setPreviousCity(String previousCity) {
		this.previousCity = previousCity;
	}

	public String getPreviousZipcode() {
		return previousZipcode;
	}

	public void setPreviousZipcode(String previousZipcode) {
		this.previousZipcode = previousZipcode;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDriverLicense_StateId() {
		return driverLicense_StateId;
	}

	public void setDriverLicense_StateId(String driverLicense_StateId) {
		this.driverLicense_StateId = driverLicense_StateId;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getQuestion2_Reason() {
		return question2_Reason;
	}

	public void setQuestion2_Reason(String question2_Reason) {
		this.question2_Reason = question2_Reason;
	}

	public int getIsSameCurr_as_Perm() {
		return isSameCurr_as_Perm;
	}

	public void setIsSameCurr_as_Perm(int isSameCurr_as_Perm) {
		this.isSameCurr_as_Perm = isSameCurr_as_Perm;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	
	
	
}
