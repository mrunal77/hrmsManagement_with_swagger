package com.rainier.response;

import java.util.ArrayList;
import java.util.List;

import com.rainier.beans.CandSubmissionBean;

public class CandidateSubmissionCountResponse 
{
	boolean status;
	String message;	
	List<CandSubmissionBean> candSubmissionBeans = new ArrayList<>();
	
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
	public List<CandSubmissionBean> getCandSubmissionBeans() {
		return candSubmissionBeans;
	}
	public void setCandSubmissionBeans(List<CandSubmissionBean> candSubmissionBeans) {
		this.candSubmissionBeans = candSubmissionBeans;
	}


}
