package com.rainier.beans;

public class ContactDetailsRequestBean {
	private int id;
	private int userId;
	private String personalEmail;
	private String permStreetAddress;
	private Integer permCountry;
	private Integer permState;
	private Integer permCity;
	private String permPinCode;
	private String currentStreetAddress;
	private Integer currentCountry;
	private Integer currentState;
	private Integer currentCity;
	private String currentPinCode;
	private String emergencyNumber;
	private String emergencyName;
	private String emergencyEmail;
	private int createdBy;

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

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getPermStreetAddress() {
		return permStreetAddress;
	}

	public void setPermStreetAddress(String permStreetAddress) {
		this.permStreetAddress = permStreetAddress;
	}

	public Integer getPermCountry() {
		return permCountry;
	}

	public void setPermCountry(Integer permCountry) {
		this.permCountry = permCountry;
	}

	public Integer getPermState() {
		return permState;
	}

	public void setPermState(Integer permState) {
		this.permState = permState;
	}

	public Integer getPermCity() {
		return permCity;
	}

	public void setPermCity(Integer permCity) {
		this.permCity = permCity;
	}

	public String getPermPinCode() {
		return permPinCode;
	}

	public void setPermPinCode(String permPinCode) {
		this.permPinCode = permPinCode;
	}

	public String getCurrentStreetAddress() {
		return currentStreetAddress;
	}

	public void setCurrentStreetAddress(String currentStreetAddress) {
		this.currentStreetAddress = currentStreetAddress;
	}

	public Integer getCurrentCountry() {
		return currentCountry;
	}

	public void setCurrentCountry(Integer currentCountry) {
		this.currentCountry = currentCountry;
	}

	public Integer getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Integer currentState) {
		this.currentState = currentState;
	}

	public Integer getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(Integer currentCity) {
		this.currentCity = currentCity;
	}

	public String getCurrentPinCode() {
		return currentPinCode;
	}

	public void setCurrentPinCode(String currentPinCode) {
		this.currentPinCode = currentPinCode;
	}

	public String getEmergencyNumber() {
		return emergencyNumber;
	}

	public void setEmergencyNumber(String emergencyNumber) {
		this.emergencyNumber = emergencyNumber;
	}

	public String getEmergencyName() {
		return emergencyName;
	}

	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}

	public String getEmergencyEmail() {
		return emergencyEmail;
	}

	public void setEmergencyEmail(String emergencyEmail) {
		this.emergencyEmail = emergencyEmail;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

}
