package com.rainier.beans;

import java.sql.Date;

public class RecruitmentOpeningBean {

	private int requisitionId;
	private String requisitionCode;
	private Date openingDate;
	private Date closingDate;
	private String openingForJobTitle;
	private int noOfVacancy;
	private String additionalInfo;
	private String jobDescription;
	private String requiredSkills;
	private String requiredQualification;
	private String requiredExperianceRange;
	private String employmentStatus;
	private int priority;

	private String recruiters;
	private String recruitersMailId;
	private String locationFor;
	private String industryType;

	public int getRequisitionId() {
		return requisitionId;
	}

	public void setRequisitionId(int requisitionId) {
		this.requisitionId = requisitionId;
	}

	public String getRequisitionCode() {
		return requisitionCode;
	}

	public void setRequisitionCode(String requisitionCode) {
		this.requisitionCode = requisitionCode;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getOpeningForJobTitle() {
		return openingForJobTitle;
	}

	public void setOpeningForJobTitle(String openingForJobTitle) {
		this.openingForJobTitle = openingForJobTitle;
	}

	public int getNoOfVacancy() {
		return noOfVacancy;
	}

	public void setNoOfVacancy(int noOfVacancy) {
		this.noOfVacancy = noOfVacancy;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public String getRequiredQualification() {
		return requiredQualification;
	}

	public void setRequiredQualification(String requiredQualification) {
		this.requiredQualification = requiredQualification;
	}

	public String getRequiredExperianceRange() {
		return requiredExperianceRange;
	}

	public void setRequiredExperianceRange(String requiredExperianceRange) {
		this.requiredExperianceRange = requiredExperianceRange;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getRecruiters() {
		return recruiters;
	}

	public void setRecruiters(String recruiters) {
		this.recruiters = recruiters;
	}

	public String getRecruitersMailId() {
		return recruitersMailId;
	}

	public void setRecruitersMailId(String recruitersMailId) {
		this.recruitersMailId = recruitersMailId;
	}

	public String getLocationFor() {
		return locationFor;
	}

	public void setLocationFor(String locationFor) {
		this.locationFor = locationFor;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

}
