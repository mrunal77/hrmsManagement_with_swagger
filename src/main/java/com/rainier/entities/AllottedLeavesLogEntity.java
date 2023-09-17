package com.rainier.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "main_allottedleaveslog")
public class AllottedLeavesLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "assignedleaves")
	private int assignedLeaves;

	@Column(name = "totalleaves")
	private String totalLeaves;

	@Column(name = "year")
	private String year;

	@Column(name = "createdby")
	private Integer createdBy;

	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@Column(name = "createddate")
	private Date createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifieddate;

	@Column(name = "isactive")
	private int isActive;

	public int getId() {
		return id;
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

	public int getAssignedLeaves() {
		return assignedLeaves;
	}

	public void setAssignedLeaves(int assignedLeaves) {
		this.assignedLeaves = assignedLeaves;
	}

	public String getTotalLeaves() {
		return totalLeaves;
	}

	public void setTotalLeaves(String totalLeaves) {
		this.totalLeaves = totalLeaves;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

}
