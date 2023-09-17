package com.rainier.beans;

import java.util.List;

public class ReportingManagerResponseBean {
	private boolean status;
	private String message;
	private List<ReportingManagerBean> reportingManagerList;

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

	public List<ReportingManagerBean> getReportingManagerList() {
		return reportingManagerList;
	}

	public void setReportingManagerList(List<ReportingManagerBean> reportingManagerList) {
		this.reportingManagerList = reportingManagerList;
	}

	

}
