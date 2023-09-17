package com.rainier.beans;

import java.util.List;

public class SaveTimesheetRequestBean {
	private int userId;
	private Integer repId;
	private Short year;
	private Short month;
	private Short weekNo;
	private Short yearWeek;
	private String status;
	private double totalHrs;
	private int clientId;
	private List<SavingTimeSheetReqBean> timeSheetBean;
	private Integer createdOrModifiedBy;

	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Integer getRepId() {
		return repId;
	}

	public void setRepId(Integer repId) {
		this.repId = repId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Short getYear() {
		return year;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	public Short getMonth() {
		return month;
	}

	public void setMonth(Short month) {
		this.month = month;
	}

	public Short getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(Short weekNo) {
		this.weekNo = weekNo;
	}

	public Short getYearWeek() {
		return yearWeek;
	}

	public void setYearWeek(Short yearWeek) {
		this.yearWeek = yearWeek;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalHrs() {
		return totalHrs;
	}

	public void setTotalHrs(double totalHrs) {
		this.totalHrs = totalHrs;
	}

	public List<SavingTimeSheetReqBean> getTimeSheetBean() {
		return timeSheetBean;
	}

	public void setTimeSheetBean(List<SavingTimeSheetReqBean> timeSheetBean) {
		this.timeSheetBean = timeSheetBean;
	}

	public Integer getCreatedOrModifiedBy() {
		return createdOrModifiedBy;
	}

	public void setCreatedOrModifiedBy(Integer createdOrModifiedBy) {
		this.createdOrModifiedBy = createdOrModifiedBy;
	}

}
