package com.rainier.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_leavemanagement_summary")
public class LeaveManagementEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "leavemgmt_id")
	private int leaveMgmtId;

	@Column(name = "cal_startmonth")
	private int startMonth;

	@Column(name = "cal_startmonthname")
	private String startMonthName;

	@Column(name = "weekend_startday")
	private int satrtDay;

	@Column(name = "weekend_startdayname")
	private String startDayName;

	@Column(name = "weekend_endday")
	private int endDay;

	@Column(name = "weekend_enddayname")
	private String endDayName;

	@Column(name = "businessunit_name")
	private String businessUnit;

	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "hours_day")
	private int workingHours;

	@Column(name = "is_satholiday")
	private String satHoliday;

	@Column(name = "is_halfday")
	private String isHalfDay;

	@Column(name = "is_leavetransfer")
	private String isLeaveTransfer;

	@Column(name = "is_skipholidays")
	private String isSkipHolidays;

	@Column(name = "description")
	private String description;

	@Column(name = "isactive")
	private int isActive;

	@Column(name = "createdby")
	private Integer createdBy;
	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "createddate")
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLeaveMgmtId() {
		return leaveMgmtId;
	}

	public void setLeaveMgmtId(int leaveMgmtId) {
		this.leaveMgmtId = leaveMgmtId;
	}

	public int getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

	public String getStartMonthName() {
		return startMonthName;
	}

	public void setStartMonthName(String startMonthName) {
		this.startMonthName = startMonthName;
	}

	public int getSatrtDay() {
		return satrtDay;
	}

	public void setSatrtDay(int satrtDay) {
		this.satrtDay = satrtDay;
	}

	public String getStartDayName() {
		return startDayName;
	}

	public void setStartDayName(String startDayName) {
		this.startDayName = startDayName;
	}

	public int getEndDay() {
		return endDay;
	}

	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}

	public String getEndDayName() {
		return endDayName;
	}

	public void setEndDayName(String endDayName) {
		this.endDayName = endDayName;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(int workingHours) {
		this.workingHours = workingHours;
	}

	public String getSatHoliday() {
		return satHoliday;
	}

	public void setSatHoliday(String satHoliday) {
		this.satHoliday = satHoliday;
	}

	public String getIsHalfDay() {
		return isHalfDay;
	}

	public void setIsHalfDay(String isHalfDay) {
		this.isHalfDay = isHalfDay;
	}

	public String getIsLeaveTransfer() {
		return isLeaveTransfer;
	}

	public void setIsLeaveTransfer(String isLeaveTransfer) {
		this.isLeaveTransfer = isLeaveTransfer;
	}

	public String getIsSkipHolidays() {
		return isSkipHolidays;
	}

	public void setIsSkipHolidays(String isSkipHolidays) {
		this.isSkipHolidays = isSkipHolidays;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

}
