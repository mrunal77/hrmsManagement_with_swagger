package com.rainier.dto.responseBean;

public class ProjectRespBean {
	
	private Object listofProject;
	private String message;
	private Boolean status;
	private Object clientList;
	
	

	public Object getClientList() {
		return clientList;
	}

	public void setClientList(Object clientList) {
		this.clientList = clientList;
	}

	public Object getListofProject() {
		return listofProject;
	}

	public void setListofProject(Object listofProject) {
		this.listofProject = listofProject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
