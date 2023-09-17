package com.rainier.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "main_leaverequest")
public class MyLeaveRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "leavetypeid")
    private Integer leaveTypeId;

    @Column(name = "leaveType")
    private String leaveType;

    @Column(name = "reason")
    private String reason;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "leaveday")
    private String leaveFor;

    @Column(name = "no_of_days")
    private float days;

    @Column(name = "leavestatus")
    private String leaveStatus;

    @Column(name = "rep_mang_id")
    private Integer reportingManagerId;

    @Column(name = "reportingManager")
    private String reportingManager;

    @Column(name = "hr_id")
    private Integer hrId;

    @Column(name = "availableLeaves"/* ,nullable=false,insertable=true,updatable=true */)
    private float availableLeaves;

    // cmt by jai
    
  /*  @Column(name = "isactive")
    private byte isActive;*/
    
    @Column(name = "isactive")
    private int isactive;

    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    @Column(name = "createddate")
    private Timestamp createdDdate;

    @Column(name = "modifiedby")
    private Integer modifiedBy;

    @Column(name = "createdby")
    private Integer createdBy;

    @Column(name = "appliedleavescount")
    private Integer appliedLeavescount;
    
    @Column(name="email_address")
    private String email;
    
    @Column(name="name")
    private String name;
    

    /*
     * @Column(name="availableLeaves",nullable=false,updatable=false) private String
     * avilableCasualLeaves;
     *
     * @Column(name="availableLeaves",nullable=false,updatable=false) private String
     * avilableSickLeaves;
     */
    
    
    public int getId() {
        return id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Integer leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getLeaveFor() {
        return leaveFor;
    }

    public void setLeaveFor(String leaveFor) {
        this.leaveFor = leaveFor;
    }

    public float getDays() {
        return days;
    }

    public void setDays(float days) {
        this.days = days;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public Integer getReportingManagerId() {
        return reportingManagerId;
    }

    public void setReportingManagerId(Integer reportingManagerId) {
        this.reportingManagerId = reportingManagerId;
    }

    public String getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Integer getHrId() {
        return hrId;
    }

    public void setHrId(Integer hrId) {
        this.hrId = hrId;
    }

    public float getAvailableLeaves() {
        return availableLeaves;
    }

    public void setAvailableLeaves(float availableLeaves) {
        this.availableLeaves = availableLeaves;
    }

   

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Timestamp getCreatedDdate() {
        return createdDdate;
    }

    public void setCreatedDdate(Timestamp createdDdate) {
        this.createdDdate = createdDdate;
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

    public Integer getAppliedLeavescount() {
        return appliedLeavescount;
    }

    public void setAppliedLeavescount(Integer appliedLeavescount) {
        this.appliedLeavescount = appliedLeavescount;
    }

    /*
     * public String getAvilableCasualLeaves() { return avilableCasualLeaves; }
     *
     * public void setAvilableCasualLeaves(String avilableCasualLeaves) {
     * this.avilableCasualLeaves = avilableCasualLeaves; }
     *
     * public String getAvilableSickLeaves() { return avilableSickLeaves; }
     *
     * public void setAvilableSickLeaves(String avilableSickLeaves) {
     * this.avilableSickLeaves = avilableSickLeaves; }
     */

}
