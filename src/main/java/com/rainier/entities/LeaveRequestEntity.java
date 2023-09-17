package com.rainier.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_leaverequest_summary")
public class LeaveRequestEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Id;

	@Column(name = "leave_req_id")
	private int leaveReqId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "reason")
	private String reason;

	@Column(name = "leavestatus")
	private String leaveStatus;

	@Column(name = "leavetypeid")
	private int leaveTypeId;

	@Column(name = "leavetype_name")
	private String leaveType;

	@Column(name = "from_date")
	private String fromDate;

	@Column(name = "to_date")
	private String toDate;

	@Column(name = "no_of_days")
	private int days;

	@Column(name = "createddate")
	private String appliedOn;

	@Column(name = "rep_mang_id")
	private int reportingManagerId;

	@Column(name = "rep_manager_name")
	private String reportingManager;

	@Column(name = "appliedleavescount")
	private Double appliedLeavesCount;

	@Column(name = "isactive")
	private int isActive;

	@Column(name = "createdby")
	private Integer createdBy;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

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

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getLeaveReqId() {
		return leaveReqId;
	}

	public void setLeaveReqId(int leaveReqId) {
		this.leaveReqId = leaveReqId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(String appliedOn) {
		this.appliedOn = appliedOn;
	}

	public int getReportingManagerId() {
		return reportingManagerId;
	}

	public void setReportingManagerId(int reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}

	public Double getAppliedLeavesCount() {
		return appliedLeavesCount;
	}

	public void setAppliedLeavesCount(Double appliedLeavesCount) {
		this.appliedLeavesCount = appliedLeavesCount;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

}
