package com.rainier.beans;

public class SelectedDocumentResponseBean {
	private String message;
	private boolean status;
	private Object listOfDocuments;

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

	public Object getListOfDocuments() {
		return listOfDocuments;
	}

	public void setListOfDocuments(Object listOfDocuments) {
		this.listOfDocuments = listOfDocuments;
	}

}
