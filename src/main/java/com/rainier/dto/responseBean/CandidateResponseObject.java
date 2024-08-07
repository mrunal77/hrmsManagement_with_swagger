package com.rainier.dto.responseBean;

import com.rainier.entities.BenchSalesAddCandidateEntity;

import java.util.List;

public class CandidateResponseObject {
	
	private String recruiterName;
	
	List<BenchSalesAddCandidateEntity> candidList;

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public List<BenchSalesAddCandidateEntity> getCandidList() {
		return candidList;
	}

	public void setCandidList(List<BenchSalesAddCandidateEntity> candidList) {
		this.candidList = candidList;
	}
}
