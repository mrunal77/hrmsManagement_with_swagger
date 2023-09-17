package com.rainier.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_jobtitles")
public class JobTitlesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	@Column(name = "jobtitlecode")
    private
    String jobTitleCode;

	@Column(name = "jobtitlename")
	private
	String jobTitleName;

	@Column(name = "jobdescription")
	private
	String jobDescription;

	@Column(name = "minexperiencerequired")
	private
	String minExpReq;

	@Column(name = "jobpaygradecode")
	private
	String jobPayGradeCode;

	@Column(name = "jobpayfrequency")
	private
	String jobPayFrequency;

	@JsonIgnore
	@Column(name = "createdby", updatable = false)
	private
	Integer createdBy;

	@JsonIgnore
	@Column(name = "modifiedby")
	private
	Integer modifiedBy;

	@JsonIgnore
	@Column(name = "createddate", updatable = false)
	private
	Timestamp createdDate;

	@JsonIgnore
	@Column(name = "modifieddate")
	private
	Timestamp modifiedDate;

	@JsonIgnore
	@Column(name = "isactive")
	private
	int isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobTitleCode() {
		return jobTitleCode;
	}

	public void setJobTitleCode(String jobTitleCode) {
		this.jobTitleCode = jobTitleCode;
	}

	public String getJobTitleName() {
		return jobTitleName;
	}

	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getMinExpReq() {
		return minExpReq;
	}

	public void setMinExpReq(String minExpReq) {
		this.minExpReq = minExpReq;
	}

	public String getJobPayGradeCode() {
		return jobPayGradeCode;
	}

	public void setJobPayGradeCode(String jobPayGradeCode) {
		this.jobPayGradeCode = jobPayGradeCode;
	}

	public String getJobPayFrequency() {
		return jobPayFrequency;
	}

	public void setJobPayFrequency(String jobPayFrequency) {
		this.jobPayFrequency = jobPayFrequency;
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

}
