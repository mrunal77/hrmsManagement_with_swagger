package com.rainier.beans;

import java.sql.Timestamp;
import java.util.Date;

public class PersonalDetailsResponseBean {
	private int id;
	private int userId;
	private String gender;
	private int genderId;
	private String maritalStatus;
	private int maritalStatusId;
	private String nationality;
	private int nationalityId;
	private String ethinicCode;
	private int ethinicCodeId;
	private String raceCode;
	private int raceCodeId;
	private String language;
	private int languageId;
	private String dateOfBirth;
	private String bloodGroup;
	private String passport;
	private Timestamp passportExpDate;
	private String drivingLicence;
	private Timestamp drivingLicenceExpDate;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public int getMaritalStatusId() {
		return maritalStatusId;
	}

	public void setMaritalStatusId(int maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(int nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getEthinicCode() {
		return ethinicCode;
	}

	public void setEthinicCode(String ethinicCode) {
		this.ethinicCode = ethinicCode;
	}

	public int getEthinicCodeId() {
		return ethinicCodeId;
	}

	public void setEthinicCodeId(int ethinicCodeId) {
		this.ethinicCodeId = ethinicCodeId;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public int getRaceCodeId() {
		return raceCodeId;
	}

	public void setRaceCodeId(int raceCodeId) {
		this.raceCodeId = raceCodeId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Timestamp getPassportExpDate() {
		return passportExpDate;
	}

	public void setPassportExpDate(Timestamp passportExpDate) {
		this.passportExpDate = passportExpDate;
	}

	public String getDrivingLicence() {
		return drivingLicence;
	}

	public void setDrivingLicence(String drivingLicence) {
		this.drivingLicence = drivingLicence;
	}

	public Timestamp getDrivingLicenceExpDate() {
		return drivingLicenceExpDate;
	}

	public void setDrivingLicenceExpDate(Timestamp drivingLicenceExpDate) {
		this.drivingLicenceExpDate = drivingLicenceExpDate;
	}

}
