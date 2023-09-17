package com.rainier.beans;

public class RecruitmentAssignStatusBean {
	private int id;
	private int assignReportId;
	private int jobPostingId;
	private int candidateApplyId;
	private String assignReportStatus;
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
	public int getAssignReportId() {
		return assignReportId;
	}
	public void setAssignReportId(int assignReportId) {
		this.assignReportId = assignReportId;
	}
	
	
	


}
