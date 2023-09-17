package com.rainier.beans;

import java.util.List;

public class JobTitleListResponseBean {
	private boolean status;
	private String message;
	private List<JobTitleBean> jobTitleList;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<JobTitleBean> getJobTitleList() {
		return jobTitleList;
	}

	public void setJobTitleList(List<JobTitleBean> jobTitleList) {
		this.jobTitleList = jobTitleList;
	}

}
