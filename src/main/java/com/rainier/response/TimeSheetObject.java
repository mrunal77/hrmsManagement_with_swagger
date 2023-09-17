package com.rainier.response;

import java.sql.Date;
import java.sql.Timestamp;

public class TimeSheetObject {
	
		private int saveId;

	
		private int employeeId;

		
		private int clientId;

		
		private String clientName;

		
		private int projectId;

		
		private String projectName;

		private int projectTaskId;

		private String projectTaskName;

		private int year;

		private Short month;

		private Short weekNo;

		private Short cal_week;

		private String mon_hours;

		private Date mon_date;

		private String tue_hours;

		private Date tue_date;

		private String wed_hours;

		private Date wed_Date;

		private String thurs_hours;

		private Date thurs_Date;

		private String fri_hours;

		private Date fri_Date;

		private String sat_hours;

		private Date sat_Date;

		private String sun_hours;

		private Date sun_Date;

		private String TotalWeekHours;

		private Timestamp created_Date;

		private int createdBy;

		private int modifiedBy;

		private Timestamp modifiedDate;

		private Short isActive;

		private String status;
		
		private String reportingManagerName ;

		public String getReportingManagerName() {
			return reportingManagerName;
		}

		public void setReportingManagerName(String reportingManagerName) {
			this.reportingManagerName = reportingManagerName;
		}

		public int getSaveId() {
			return saveId;
		}

		public void setSaveId(int saveId) {
			this.saveId = saveId;
		}

		public int getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}

		public int getProjectId() {
			return projectId;
		}

		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public int getProjectTaskId() {
			return projectTaskId;
		}

		public void setProjectTaskId(int projectTaskId) {
			this.projectTaskId = projectTaskId;
		}

		public String getProjectTaskName() {
			return projectTaskName;
		}

		public void setProjectTaskName(String projectTaskName) {
			this.projectTaskName = projectTaskName;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
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

		public Short getCal_week() {
			return cal_week;
		}

		public void setCal_week(Short cal_week) {
			this.cal_week = cal_week;
		}

		public String getMon_hours() {
			return mon_hours;
		}

		public void setMon_hours(String mon_hours) {
			this.mon_hours = mon_hours;
		}

		public Date getMon_date() {
			return mon_date;
		}

		public void setMon_date(Date mon_date) {
			this.mon_date = mon_date;
		}

		public String getTue_hours() {
			return tue_hours;
		}

		public void setTue_hours(String tue_hours) {
			this.tue_hours = tue_hours;
		}

		public Date getTue_date() {
			return tue_date;
		}

		public void setTue_date(Date tue_date) {
			this.tue_date = tue_date;
		}

		public String getWed_hours() {
			return wed_hours;
		}

		public void setWed_hours(String wed_hours) {
			this.wed_hours = wed_hours;
		}

		public Date getWed_Date() {
			return wed_Date;
		}

		public void setWed_Date(Date wed_Date) {
			this.wed_Date = wed_Date;
		}

		public String getThurs_hours() {
			return thurs_hours;
		}

		public void setThurs_hours(String thurs_hours) {
			this.thurs_hours = thurs_hours;
		}

		public Date getThurs_Date() {
			return thurs_Date;
		}

		public void setThurs_Date(Date thurs_Date) {
			this.thurs_Date = thurs_Date;
		}

		public String getFri_hours() {
			return fri_hours;
		}

		public void setFri_hours(String fri_hours) {
			this.fri_hours = fri_hours;
		}

		public Date getFri_Date() {
			return fri_Date;
		}

		public void setFri_Date(Date fri_Date) {
			this.fri_Date = fri_Date;
		}

		public String getSat_hours() {
			return sat_hours;
		}

		public void setSat_hours(String sat_hours) {
			this.sat_hours = sat_hours;
		}

		public Date getSat_Date() {
			return sat_Date;
		}

		public void setSat_Date(Date sat_Date) {
			this.sat_Date = sat_Date;
		}

		public String getSun_hours() {
			return sun_hours;
		}

		public void setSun_hours(String sun_hours) {
			this.sun_hours = sun_hours;
		}

		public Date getSun_Date() {
			return sun_Date;
		}

		public void setSun_Date(Date sun_Date) {
			this.sun_Date = sun_Date;
		}

		public String getTotalWeekHours() {
			return TotalWeekHours;
		}

		public void setTotalWeekHours(String totalWeekHours) {
			TotalWeekHours = totalWeekHours;
		}

		public Timestamp getCreated_Date() {
			return created_Date;
		}

		public void setCreated_Date(Timestamp created_Date) {
			this.created_Date = created_Date;
		}

		public int getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(int createdBy) {
			this.createdBy = createdBy;
		}

		public int getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(int modifiedBy) {
			this.modifiedBy = modifiedBy;
		}

		public Timestamp getModifiedDate() {
			return modifiedDate;
		}

		public void setModifiedDate(Timestamp modifiedDate) {
			this.modifiedDate = modifiedDate;
		}

		public Short getIsActive() {
			return isActive;
		}

		public void setIsActive(Short isActive) {
			this.isActive = isActive;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getClientId() {
			return clientId;
		}

		public void setClientId(int clientId) {
			this.clientId = clientId;
		}

		public String getClientName() {
			return clientName;
		}

		public void setClientName(String clientName) {
			this.clientName = clientName;
		}


}
