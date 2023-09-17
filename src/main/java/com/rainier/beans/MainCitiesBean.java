package com.rainier.beans;

import java.sql.Timestamp;

public class MainCitiesBean {

	private int id;
	private int countryId;
	private int stateId;
	private String cityName;
	private int cityIdOrg;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	
	private int country_id_org;
	private int state_id_org;
	private String countryName;
	private String stateName;
	private int isactive;
	
	
	

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityIdOrg() {
		return cityIdOrg;
	}

	public void setCityIdOrg(int cityIdOrg) {
		this.cityIdOrg = cityIdOrg;
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

	public int getCountry_id_org() {
		return country_id_org;
	}

	public void setCountry_id_org(int country_id_org) {
		this.country_id_org = country_id_org;
	}

	public int getState_id_org() {
		return state_id_org;
	}

	public void setState_id_org(int state_id_org) {
		this.state_id_org = state_id_org;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	
}
