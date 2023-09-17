package com.rainier.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_states")
public class Main_StatesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "countryid")
	private int countryId;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="countryid") private Main_CountriesEntity main_Country;
	 */

	@Column(name = "state")
	private String stateName;

	@Column(name = "statecode")
	private String stateCode;

	@Column(name = "state_id_org", unique = true, nullable = false)
	private int stateIdOrg;

	@Column(name = "createddate")
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@Column(name = "isactive")
	private int isactive;

	/*
	 * public Main_CountriesEntity getMain_Country() { return main_Country; }
	 * 
	 * public void setMain_Country(Main_CountriesEntity main_Country) {
	 * this.main_Country = main_Country; }
	 */

	public int getId() {
		return id;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public int getStateIdOrg() {
		return stateIdOrg;
	}

	public void setStateIdOrg(int stateIdOrg) {
		this.stateIdOrg = stateIdOrg;
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

}
