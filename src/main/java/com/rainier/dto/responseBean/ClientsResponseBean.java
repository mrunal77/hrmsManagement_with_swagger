package com.rainier.dto.responseBean;

public class ClientsResponseBean {
	private String message;
	private boolean status;
	private Object clientList;
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
	public Object getClientList() {
		return clientList;
	}
	public void setClientList(Object clientList) {
		this.clientList = clientList;
	}
	
	
	

}
