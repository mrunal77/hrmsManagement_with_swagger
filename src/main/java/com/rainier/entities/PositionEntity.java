package com.rainier.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_positions")
public class PositionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "positionname")
	private String positionName;

	// @Column(name = "jobtitleid")
	// private Integer jobTitleId;

	// @Column(name = "jobtitleid")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "jobtitleid", referencedColumnName = "id")
	private JobTitlesEntity jobTitleId;

	@JsonIgnore
	@Column(name = "isactive")
	private int isActive;

	@JsonIgnore
	@Column(name = "createdby", updatable = false)
	private int createdBy;

	@JsonIgnore
	@Column(name = "modifiedby")
	private int modifiedBy;

	@JsonIgnore
	@Column(name = "createddate", updatable = false)
	private Timestamp createdDate;

	@JsonIgnore
	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

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

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/*
	 * public Integer getJobTitleId() { return jobTitleId; }
	 * 
	 * public void setJobTitleId(Integer jobTitleId) { this.jobTitleId = jobTitleId;
	 * }
	 */

	public int getIsActive() {
		return isActive;
	}

	public JobTitlesEntity getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(JobTitlesEntity jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

}
