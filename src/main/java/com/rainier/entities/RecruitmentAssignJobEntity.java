package com.rainier.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "RecruitmentAssignJobEntity")

public class RecruitmentAssignJobEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	//@GeneratedValue

	@Column(name = "id")
	private int id;
	

	@Column(name = "experience")
	private String experience;

	@Column(name = "skill")
	private String skill;

	@Column(name = "jobPostingId")
	private int jobPostingId;

	@Column(name = "candidateApplyId")
	private String candidateApplyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getCandidateApplyId() {
		return candidateApplyId;
	}

	public void setCandidateApplyId(String candidateApplyId) {
		this.candidateApplyId = candidateApplyId;
	}

	
}
