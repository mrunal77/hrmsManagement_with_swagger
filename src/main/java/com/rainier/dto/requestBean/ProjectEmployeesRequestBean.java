package com.rainier.dto.requestBean;

public class ProjectEmployeesRequestBean {
	private int id;
	private int projectId;
	private int[] employeeIds;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int[] getEmployeeId() {
		return employeeIds;
	}

	public void setEmployeeId(int[] employeeIds) {
		this.employeeIds = employeeIds;
	}

}
