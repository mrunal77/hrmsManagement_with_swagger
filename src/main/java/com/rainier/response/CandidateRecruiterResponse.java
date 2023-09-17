package com.rainier.response;

import java.util.ArrayList;
import java.util.List;

import com.rainier.dto.responseBean.CandidateResponseObject;

public class CandidateRecruiterResponse
{
	private boolean status;
	private String message;
	private Object privillegesList;
	private List<CandidateResponseObject>  candidateAndRecruiters = new ArrayList<CandidateResponseObject>();



	public List<CandidateResponseObject> getCandidateAndRecruiters() {
		return candidateAndRecruiters;
	}

	public void setCandidateAndRecruiters(List<CandidateResponseObject> candidateAndRecruiters) {
		this.candidateAndRecruiters = candidateAndRecruiters;
	}

	public Object getPrivillegesList() {
		return privillegesList;
	}

	public void setPrivillegesList(Object privillegesList) {
		this.privillegesList = privillegesList;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
