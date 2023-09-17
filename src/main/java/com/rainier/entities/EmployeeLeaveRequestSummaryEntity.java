package com.rainier.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "main_leaverequest_summary")
public class EmployeeLeaveRequestSummaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "leave_req_id")
	private int leaveRequestId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "department_id")
	private int departmentId;

	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "bunit_id")
	private int businessUnitId;

	@Column(name = "buss_unit_name")
	private String businessUnitName;

	@Column(name = "reason")
	private String reason;

	@Column(name = "approver_comments")
	private String approverComments;

	@Column(name = "leavetypeid")
	private int leaveTypeId;

	@Column(name = "leavetype_name")
	private String leaveTypeName;

	@Column(name = "leaveday")
	private int leaveDay;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

	/*
	 * @Column(name="") public enum
	 * leaveStatus{pendingForApproval,Approved,Rejected,Cancel;};
	 */
	@Column(name = "rep_mang_id")
	private int reportingManagerId;

	@Column(name = "rep_manager_name")
	private String reportingManagerName;

	@Column(name = "hr_id")
	private int hrId;

	@Column(name = "hr_name")
	private String hrName;

	@Column(name = "no_of_days")
	private float noOfDays;

	@Column(name = "appliedleavescount")
	private float appliedLeavesCount;

	@Column(name = "is_sat_holiday")
	private int isSatHoliday;

	@Column(name = "modifieddate")
	private Timestamp modifieddate;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "createdby")
	private Integer createdBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLeaveRequestId() {
		return leaveRequestId;
	}

	public void setLeaveRequestId(int leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
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

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(int businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public int getLeaveDay() {
		return leaveDay;
	}

	public void setLeaveDay(int leaveDay) {
		this.leaveDay = leaveDay;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getReportingManagerId() {
		return reportingManagerId;
	}

	public void setReportingManagerId(int reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}

	public String getReportingManagerName() {
		return reportingManagerName;
	}

	public void setReportingManagerName(String reportingManagerName) {
		this.reportingManagerName = reportingManagerName;
	}

	public int getHrId() {
		return hrId;
	}

	public void setHrId(int hrId) {
		this.hrId = hrId;
	}

	public String getHrName() {
		return hrName;
	}

	public void setHrName(String hrName) {
		this.hrName = hrName;
	}

	public float getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(float noOfDays) {
		this.noOfDays = noOfDays;
	}

	public float getAppliedLeavesCount() {
		return appliedLeavesCount;
	}

	public void setAppliedLeavesCount(float appliedLeavesCount) {
		this.appliedLeavesCount = appliedLeavesCount;
	}

	public int getIsSatHoliday() {
		return isSatHoliday;
	}

	public void setIsSatHoliday(int isSatHoliday) {
		this.isSatHoliday = isSatHoliday;
	}

	public Timestamp getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

}
