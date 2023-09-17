package com.rainier.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class RecruitmentPostProfileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	private String multipleLocation;
	private int countryId;
	private int stateId;
	private int cityId;
	private String mobileNo;
	private String homePhoneNo;

	private String highestEducation;
	private int yearOfPassout;

	private int totalExperienceYear;
	private int totalExperienceMonth;
	private float relaventExpYear;
	private int relaventExpMonth;

	private String currentOrganization;
	private String currentCTC;
	private String noticePeriod;
	private String expectedSallary;

	private String domainSkills;
	private String additionalSkills;

	private int visaCountryId;
	private String visatype;
	private Date visaExpiryDate;

	private int exCompanyGroup;
	private String exCompanyName;
	private String uploadProfile;
	private String resumePostedDate;

	/*
	 * @Column(name="requisition_id") private int jobPostingId;
	 */

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "requisition_id", referencedColumnName = "id")
	private RecruitmentOpeningsEntity jobPostingId;
	/*
	 * @Column(name="candidateId") private int candidateApplyId;
	 */
/*
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "candidateId", referencedColumnName = "id")
	private RecruitmentApplyNowForJobEntity candidateApplyId;
*/
	@Column(name = "title")
	private String title;

	@Column(name = "candidateName")
	private String candidateName;

	@Column(name = "candidateEmailid")
	private String candidateEmailid;

	@Column(name = "isactive")
	private int isactive;

	@Column(name = "identificationId")
	private String identificationId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMultipleLocation() {
		return multipleLocation;
	}

	public void setMultipleLocation(String multipleLocation) {
		this.multipleLocation = multipleLocation;
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

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getHomePhoneNo() {
		return homePhoneNo;
	}

	public void setHomePhoneNo(String homePhoneNo) {
		this.homePhoneNo = homePhoneNo;
	}

	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public int getYearOfPassout() {
		return yearOfPassout;
	}

	public void setYearOfPassout(int yearOfPassout) {
		this.yearOfPassout = yearOfPassout;
	}

	public int getTotalExperienceYear() {
		return totalExperienceYear;
	}

	public void setTotalExperienceYear(int totalExperienceYear) {
		this.totalExperienceYear = totalExperienceYear;
	}

	public int getTotalExperienceMonth() {
		return totalExperienceMonth;
	}

	public void setTotalExperienceMonth(int totalExperienceMonth) {
		this.totalExperienceMonth = totalExperienceMonth;
	}

	public float getRelaventExpYear() {
		return relaventExpYear;
	}

	public void setRelaventExpYear(float relaventExpYear) {
		this.relaventExpYear = relaventExpYear;
	}

	public int getRelaventExpMonth() {
		return relaventExpMonth;
	}

	public void setRelaventExpMonth(int relaventExpMonth) {
		this.relaventExpMonth = relaventExpMonth;
	}

	public String getCurrentOrganization() {
		return currentOrganization;
	}

	public void setCurrentOrganization(String currentOrganization) {
		this.currentOrganization = currentOrganization;
	}

	public String getCurrentCTC() {
		return currentCTC;
	}

	public void setCurrentCTC(String currentCTC) {
		this.currentCTC = currentCTC;
	}

	public String getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public String getExpectedSallary() {
		return expectedSallary;
	}

	public void setExpectedSallary(String expectedSallary) {
		this.expectedSallary = expectedSallary;
	}

	public String getDomainSkills() {
		return domainSkills;
	}

	public void setDomainSkills(String domainSkills) {
		this.domainSkills = domainSkills;
	}

	public String getAdditionalSkills() {
		return additionalSkills;
	}

	public void setAdditionalSkills(String additionalSkills) {
		this.additionalSkills = additionalSkills;
	}

	public int getVisaCountryId() {
		return visaCountryId;
	}

	public void setVisaCountryId(int visaCountryId) {
		this.visaCountryId = visaCountryId;
	}

	public String getVisatype() {
		return visatype;
	}

	public void setVisatype(String visatype) {
		this.visatype = visatype;
	}

	public Date getVisaExpiryDate() {
		return visaExpiryDate;
	}

	public void setVisaExpiryDate(Date visaExpiryDate) {
		this.visaExpiryDate = visaExpiryDate;
	}

	public int getExCompanyGroup() {
		return exCompanyGroup;
	}

	public void setExCompanyGroup(int exCompanyGroup) {
		this.exCompanyGroup = exCompanyGroup;
	}

	public String getExCompanyName() {
		return exCompanyName;
	}

	public void setExCompanyName(String exCompanyName) {
		this.exCompanyName = exCompanyName;
	}

	public String getUploadProfile() {
		return uploadProfile;
	}

	public void setUploadProfile(String uploadProfile) {
		this.uploadProfile = uploadProfile;
	}

	public RecruitmentOpeningsEntity getJobPostingId() {
		return jobPostingId;
	}

	public void setJobPostingId(RecruitmentOpeningsEntity jobPostingId) {
		this.jobPostingId = jobPostingId;
	}

	/*public RecruitmentApplyNowForJobEntity getCandidateApplyId() {
		return candidateApplyId;
	}

	public void setCandidateApplyId(RecruitmentApplyNowForJobEntity candidateApplyId) {
		this.candidateApplyId = candidateApplyId;
	}*/

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	
	public String getCandidateEmailid() {
		return candidateEmailid;
	}

	public void setCandidateEmailid(String candidateEmailid) {
		this.candidateEmailid = candidateEmailid;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public String getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}

	public String getResumePostedDate() {
		return resumePostedDate;
	}

	public void setResumePostedDate(String resumePostedDate) {
		this.resumePostedDate = resumePostedDate;
	}
	

}
