package com.rainier.dto.requestBean;

public class PositionRequestBean {
	private int id;
	private String positionName;
	private Integer jobTitleId;
	private int createdOrModifiedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(Integer jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	public int getCreatedOrModifiedBy() {
		return createdOrModifiedBy;
	}

	public void setCreatedOrModifiedBy(int createdOrModifiedBy) {
		this.createdOrModifiedBy = createdOrModifiedBy;
	}

}
