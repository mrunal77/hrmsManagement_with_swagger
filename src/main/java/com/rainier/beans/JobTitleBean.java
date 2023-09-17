package com.rainier.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class JobTitleBean {
	private int id;
	private String jobTitleCode;
	private String jobTitleName;
	private String jobDescription;
	private String minExpReq;
	private String jobPayGradeCode;
	private String jobPayFrequency;
	@JsonIgnore
	private int createdOrModifiedBy;

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

	@JsonIgnore
	public int getCreatedOrModifiedBy() {
		return createdOrModifiedBy;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public void setCreatedOrModifiedBy(int createdOrModifiedBy) {
		this.createdOrModifiedBy = createdOrModifiedBy;
	}

}
