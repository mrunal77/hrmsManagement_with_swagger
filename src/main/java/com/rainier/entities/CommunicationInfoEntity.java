package com.rainier.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_empcommunicationdetails")
public class CommunicationInfoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "personalemail")
	private String personalEmail;

	@Column(name = "perm_streetaddress")
	private String permStreetAddress;

	@Column(name = "perm_country")
	private Integer permCountry;

	@Column(name = "perm_state")
	private Integer permState;

	@Column(name = "perm_city")
	private Integer permCity;

	@Column(name = "perm_pincode")
	private String permPinCode;

	@Column(name = "current_streetaddress")
	private String currentStreetAddress;

	@Column(name = "current_country")
	private Integer currentCountry;

	@Column(name = "current_state")
	private Integer currentState;

	@Column(name = "current_city")
	private Integer currentCity;

	@Column(name = "current_pincode")
	private String currentPinCode;

	@Column(name = "emergency_number")
	private String emergencyNumber;

	@Column(name = "emergency_name")
	private String emergencyName;

	@Column(name = "emergency_email")
	private String emergencyEmail;

	@Column(name = "createdby", updatable = false)
	private Integer createdBy;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@Column(name = "createddate", updatable = false)
	private Timestamp createdDate;

	@Column(name = "isactive")
	private int isActive;

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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

}
