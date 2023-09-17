package com.rainier.beans;

import java.util.List;

public class RecruitmentAssignJobBean {

	private int id;
	private String experience;
	private String skill;
	private int jobPostingId;
	//private int[] assignCandidateApplyId;
	private int[] candidateApplyId;
	

	
	
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public int getJobPostingId() {
		return jobPostingId;
	}
	public void setJobPostingId(int jobPostingId) {
		this.jobPostingId = jobPostingId;
	}
	/*public int[] getAssignCandidateApplyId() {
		return assignCandidateApplyId;
	}
	public void setAssignCandidateApplyId(int[] assignCandidateApplyId) {
		this.assignCandidateApplyId = assignCandidateApplyId;
	}*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int[] getCandidateApplyId() {
		return candidateApplyId;
	}
	public void setCandidateApplyId(int[] candidateApplyId) {
		this.candidateApplyId = candidateApplyId;
	}
	
	
	
	

    // private List<RecruitmentAssignCandidateApplyId> assignCandidateApplyId;

	// private int[] assignCandidateApplyId;
	
	
	
	

}
