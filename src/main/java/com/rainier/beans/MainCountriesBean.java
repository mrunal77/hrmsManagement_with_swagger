package com.rainier.beans;

import java.sql.Timestamp;

public class MainCountriesBean {

	private int id;
	private String countryName;
	private String countryCode;
	private int createdBy;
	private int modifiedBy;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	private int countryIdOrg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
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

	public int getCountryIdOrg() {
		return countryIdOrg;
	}

	public void setCountryIdOrg(int countryIdOrg) {
		this.countryIdOrg = countryIdOrg;
	}

}
