package com.rainier.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "main_emppersonaldetails")
public class EmpPersonalDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private Integer userId;

	@ManyToOne
	@JoinColumn(name = "genderid", referencedColumnName = "id")
	private GenderEntity genderId;

	@ManyToOne
	@JoinColumn(name = "maritalstatusid", referencedColumnName = "id")
	private MaritalStatusEntity maritalStatusId;

	@ManyToOne
	@JoinColumn(name = "nationalityid", referencedColumnName = "id")
	private NationalityEntity nationalityId;

	@ManyToOne
	@JoinColumn(name = "ethniccodeid", referencedColumnName = "id")
	private EthinicCodeEntity ethinicCodeId;

	@ManyToOne
	@JoinColumn(name = "racecodeid", referencedColumnName = "id")
	private RaceCodeEntity raceCodeId;

	@ManyToOne
	@JoinColumn(name = "languageid", referencedColumnName = "id")
	private LanguageEntity languageId;
	
    
	@Column(name = "dob")
	private String dateOfBirth;

	@Column(name = "bloodgroup")
	private String bloodGroup;

//	@Column(name = "identity_documents")
//	private String identityDocuments;
	
	
	@Column(name="passportExpDate")
	private Timestamp passportExpDate;
	
	@Column(name="passport")
	private String passport;
	
	@Column(name="drivingLicence")
	private String drivingLicence;
	
	@Column(name="drivingLicenceExpDate")
	private Timestamp drivingLicenceExpDate;
	
	@Column(name = "createdby", updatable = false)
	private Integer createdBy;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "createddate", updatable = false)
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@Column(name = "isactive")
	private int isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public GenderEntity getGenderId() {
		return genderId;
	}

	public void setGenderId(GenderEntity genderId) {
		this.genderId = genderId;
	}

	public MaritalStatusEntity getMaritalStatusId() {
		return maritalStatusId;
	}

	public void setMaritalStatusId(MaritalStatusEntity maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}

	public NationalityEntity getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(NationalityEntity nationalityId) {
		this.nationalityId = nationalityId;
	}

	public EthinicCodeEntity getEthinicCodeId() {
		return ethinicCodeId;
	}

	public void setEthinicCodeId(EthinicCodeEntity ethinicCodeId) {
		this.ethinicCodeId = ethinicCodeId;
	}

	public RaceCodeEntity getRaceCodeId() {
		return raceCodeId;
	}

	public void setRaceCodeId(RaceCodeEntity raceCodeId) {
		this.raceCodeId = raceCodeId;
	}

	public LanguageEntity getLanguageId() {
		return languageId;
	}

	public void setLanguageId(LanguageEntity languageId) {
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

//	public String getIdentityDocuments() {
//		return identityDocuments;
//	}
//
//	public void setIdentityDocuments(String identityDocuments) {
//		this.identityDocuments = identityDocuments;
//	}

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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Timestamp getPassportExpDate() {
		return passportExpDate;
	}

	public void setPassportExpDate(Timestamp passportExpDate) {
		this.passportExpDate = passportExpDate;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
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
