package com.rainier.entities;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class RecruitmentApplyNowForJobEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	private int applyId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "subject")
	private String subjectSkills;

	@Column(name = "coverletter")
	private String coverletter;

	@Column(name = "uploadResume")
	private String uploadResume;

	@JsonIgnore
	@Column(name = "isSubmitted")
	private Short submitted;

	@Column(name = "jobtitle")
	private String jobTitle;

	@Column(name = "locationFor")
	private String locationFor;

	@Column(name = "experience")
	private String experience;

	@Column(name = "contactNumber")
	private String contactNo;
	
	@Column(name="applidDate")
	private String jobAppliedDate;
	
	@Column(name="requisition_id")
	private int jobPostingId;
	
	/*@Column(name="candidateId")
	private int candidateApplyId;
*/
	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
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

	public String getSubjectSkills() {
		return subjectSkills;
	}

	public void setSubjectSkills(String subjectSkills) {
		this.subjectSkills = subjectSkills;
	}

	public String getCoverletter() {
		return coverletter;
	}

	public void setCoverletter(String coverletter) {
		this.coverletter = coverletter;
	}

	public String getUploadResume() {
		return uploadResume;
	}

	public void setUploadResume(String uploadResume) {
		this.uploadResume = uploadResume;
	}

	public Short getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Short submitted) {
		this.submitted = submitted;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getLocationFor() {
		return locationFor;
	}

	public void setLocationFor(String locationFor) {
		this.locationFor = locationFor;
	}

	


	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getJobAppliedDate() {
		return jobAppliedDate;
	}

	public void setJobAppliedDate(String jobAppliedDate) {
		this.jobAppliedDate = jobAppliedDate;
	}

	public int getJobPostingId() {
		return jobPostingId;
	}

	public void setJobPostingId(int jobPostingId) {
		this.jobPostingId = jobPostingId;
	}

	/*public int getCandidateApplyId() {
		return candidateApplyId;
	}

	public void setCandidateApplyId(int candidateApplyId) {
		this.candidateApplyId = candidateApplyId;
	}

	*/

	

	
	

	
	
}
