package com.rainier.dto.responseBean;

public class CommonResponseBean {
	private String message;
	private boolean status;
	private Object list;
	private Object privilleges;
	
	
	

	public Object getPrivilleges() {
		return privilleges;
	}

	public void setPrivilleges(Object privilleges) {
		this.privilleges = privilleges;
	}

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
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

	
	
}
