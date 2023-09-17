package com.rainier.entities;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "main_requisition_summary")
public class RecruitmentOpeningsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int requisitionId;
	
	@Column(name = "requisition_code")
	private String requisitionCode;

	@Column(name = "onboard_date")
	private Date openingDate;

	@Column(name = "onclosed_date")
	private Date closingDate;

	@Column(name = "jobtitle_name")
	private String openingForJobTitle;

	@Column(name = "req_no_positions")
	private int noOfVacancy;

	@Column(name = "jobdescription")
	private String jobDescription;

	@Column(name = "req_skills")
	private String requiredSkills;

	@Column(name = "req_qualification")
	private String requiredQualification;

	@Column(name = "req_exp_years")
	private String requiredExperianceRange;

	@Column(name = "additional_info")
	private String additionalInfo;
 
	@Column(name = "emp_type_name")
	private String employmentStatus;

	@Column(name = "req_priority")
	private int priority;

	@Column(name = "createdby_name")
	private String recruiters;

	@Column(name = "isactive")
	private int isactive;

	@Column(name = "createdon")
	private Timestamp createdDate;

	@Column(name = "modifiedon")
	private Timestamp modifiedDate;
	
	@Column(name = "recruitermails")
	private String recruitersMailId;
	
	@Column(name = "location")
	private String locationFor;
	
	@Column(name = "industryType")
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

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
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

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
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
