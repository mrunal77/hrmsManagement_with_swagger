package com.rainier.beans;

public class RecruitmentApplicantStatusResponseBean {
	private String message;
	private boolean status;
	private int countAll;
	private int joined;
	private int rejected;
	private int offered;
	private int inProgress;
	private Object list;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int getCountAll() {
		return countAll;
	}
	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}
	public Object getList() {
		return list;
	}
	public void setList(Object list) {
		this.list = list;
	}
	public int getJoined() {
		return joined;
	}
	public void setJoined(int joined) {
		this.joined = joined;
	}
	
	public int getRejected() {
		return rejected;
	}
	public void setRejected(int rejected) {
		this.rejected = rejected;
	}
	public int getOffered() {
		return offered;
	}
	public void setOffered(int offered) {
		this.offered = offered;
	}
	public int getInProgress() {
		return inProgress;
	}
	public void setInProgress(int inProgress) {
		this.inProgress = inProgress;
	}
	
	
}
