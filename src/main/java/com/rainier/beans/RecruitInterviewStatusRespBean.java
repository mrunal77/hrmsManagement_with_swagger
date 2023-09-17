package com.rainier.beans;

public class RecruitInterviewStatusRespBean {
	private String message;
	private boolean status;
	private int countAll;
	private int countSelected;
	private int countRejected;
	private int countInprocess;
	private int countOnHold;
	private int countComplete;
	private Object listOfInterviewStatus;
	
	
	public Object getListOfInterviewStatus() {
		return listOfInterviewStatus;
	}
	public void setListOfInterviewStatus(Object listOfInterviewStatus) {
		this.listOfInterviewStatus = listOfInterviewStatus;
	}
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
	public int getCountSelected() {
		return countSelected;
	}
	public void setCountSelected(int countSelected) {
		this.countSelected = countSelected;
	}
	public int getCountRejected() {
		return countRejected;
	}
	public void setCountRejected(int countRejected) {
		this.countRejected = countRejected;
	}
	public int getCountInprocess() {
		return countInprocess;
	}
	public void setCountInprocess(int countInprocess) {
		this.countInprocess = countInprocess;
	}
	public int getCountOnHold() {
		return countOnHold;
	}
	public void setCountOnHold(int countOnHold) {
		this.countOnHold = countOnHold;
	}
	public int getCountComplete() {
		return countComplete;
	}
	public void setCountComplete(int countComplete) {
		this.countComplete = countComplete;
	}
	
	

}
