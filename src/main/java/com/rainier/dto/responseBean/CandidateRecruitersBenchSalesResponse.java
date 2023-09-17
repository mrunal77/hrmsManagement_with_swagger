package com.rainier.dto.responseBean;

import java.util.List;
import java.util.Set;

import com.rainier.entities.BenchSalesAddCandidateEntity;
import com.rainier.entities.User;

public class CandidateRecruitersBenchSalesResponse {
 private List<User>recruiters;
 private List<BenchSalesAddCandidateEntity>candidates;
public List<User> getRecruiters() {
	return recruiters;
}
public void setRecruiters(List<User> recruiters) {
	this.recruiters = recruiters;
}
public List<BenchSalesAddCandidateEntity> getCandidates() {
	return candidates;
}
public void setCandidates(List<BenchSalesAddCandidateEntity> candidates) {
	this.candidates = candidates;
}
 
}
