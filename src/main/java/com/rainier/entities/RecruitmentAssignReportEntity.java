package com.rainier.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table

public class RecruitmentAssignReportEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int jobPostingId;
	private int candidateApplyId;
	private String assignReportStatus;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "assignReportId", referencedColumnName = "id")
	private RecruitmentAssignJobEntity assignReportId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJobPostingId() {
		return jobPostingId;
	}

	public void setJobPostingId(int jobPostingId) {
		this.jobPostingId = jobPostingId;
	}

	public int getCandidateApplyId() {
		return candidateApplyId;
	}

	public void setCandidateApplyId(int candidateApplyId) {
		this.candidateApplyId = candidateApplyId;
	}

	public String getAssignReportStatus() {
		return assignReportStatus;
	}

	public void setAssignReportStatus(String assignReportStatus) {
		this.assignReportStatus = assignReportStatus;
	}

	public RecruitmentAssignJobEntity getAssignReportId() {
		return assignReportId;
	}

	public void setAssignReportId(RecruitmentAssignJobEntity assignReportId) {
		this.assignReportId = assignReportId;
	}
	
	
}
