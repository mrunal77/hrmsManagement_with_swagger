package com.rainier.entities;




import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RecruitmentInterviewScheduleEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private int requisitionId;
	
	private int candidateId;
	private String candidateName;
	private String interviewerName;
	private String locationForInterview;
	private int countryId;
	private int stateId;
	private int cityId;
	private String interviewType;
	private Date interviewDate;
	
	@Column(name="interviewtime")
	private String interviewtime;

	@Column(name="jobtitlename")
	private String interviewName;
	
	@Column(name="interviewstatus")
	private String interviewStatus;
	
	@Column(name="interviewerfeedback")
	private String interviewerFeedback;

	@Column(name="interviewercomments")
	private String interviewerComments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequisitionId() {
		return requisitionId;
	}

	public void setRequisitionId(int requisitionId) {
		this.requisitionId = requisitionId;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getLocationForInterview() {
		return locationForInterview;
	}

	public void setLocationForInterview(String locationForInterview) {
		this.locationForInterview = locationForInterview;
	}

	
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getInterviewtime() {
		return interviewtime;
	}

	public void setInterviewtime(String interviewtime) {
		this.interviewtime = interviewtime;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getInterviewerFeedback() {
		return interviewerFeedback;
	}

	public void setInterviewerFeedback(String interviewerFeedback) {
		this.interviewerFeedback = interviewerFeedback;
	}

	public String getInterviewerComments() {
		return interviewerComments;
	}

	public void setInterviewerComments(String interviewerComments) {
		this.interviewerComments = interviewerComments;
	}

	
	// Setters and Getters.
	
}
