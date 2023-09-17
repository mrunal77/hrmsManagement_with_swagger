package com.rainier.beans;

public class LeavesResponseBean {
	private String message;
	private boolean status;
	private int countPending;
	private int countAll;
	private int countApproved;
	private int countRejected;
	private int countCancel;
	private Object listOfLeaves;

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

	public int getCountPending() {
		return countPending;
	}

	public void setCountPending(int countPending) {
		this.countPending = countPending;
	}

	public int getCountAll() {
		return countAll;
	}

	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}

	public int getCountApproved() {
		return countApproved;
	}

	public void setCountApproved(int countApproved) {
		this.countApproved = countApproved;
	}

	public int getCountRejected() {
		return countRejected;
	}

	public void setCountRejected(int countRejected) {
		this.countRejected = countRejected;
	}

	public int getCountCancel() {
		return countCancel;
	}

	public void setCountCancel(int countCancel) {
		this.countCancel = countCancel;
	}

	public Object getListOfLeaves() {
		return listOfLeaves;
	}

	public void setListOfLeaves(Object listOfLeaves) {
		this.listOfLeaves = listOfLeaves;
	}
}
