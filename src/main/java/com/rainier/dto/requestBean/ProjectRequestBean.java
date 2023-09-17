package com.rainier.dto.requestBean;

public class ProjectRequestBean {
	private int projectId;
	private String projectName;
	private String projectStatus;
	// private int baseProject;
	private String description;
	private int clientId;
	private int currencyId;
	private String projectType;
	// private Boolean leadApproveTs;
	private int estimatedHrs;
	private String startDate;
	private String endDate;
	// private String estimatedDate;
	// private String hold_date;
	// private String initiatedDate;
	// private String completedDate;
	private int createdOrModifiedBy;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	/*
	 * public int getBaseProject() { return baseProject; }
	 * 
	 * public void setBaseProject(int baseProject) { this.baseProject = baseProject;
	 * }
	 */

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	/*
	 * public Boolean getLeadApproveTs() { return leadApproveTs; }
	 * 
	 * public void setLeadApproveTs(Boolean leadApproveTs) { this.leadApproveTs =
	 * leadApproveTs; }
	 */

	public int getEstimatedHrs() {
		return estimatedHrs;
	}

	public void setEstimatedHrs(int estimatedHrs) {
		this.estimatedHrs = estimatedHrs;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/*
	 * public String getEstimatedDate() { return estimatedDate; }
	 * 
	 * public void setEstimatedDate(String estimatedDate) { this.estimatedDate =
	 * estimatedDate; }
	 */

	/*
	 * public String getHold_date() { return hold_date; }
	 * 
	 * public void setHold_date(String hold_date) { this.hold_date = hold_date; }
	 */

	/*
	 * public String getInitiatedDate() { return initiatedDate; }
	 * 
	 * public void setInitiatedDate(String initiatedDate) { this.initiatedDate =
	 * initiatedDate; }
	 */

	/*
	 * public String getCompletedDate() { return completedDate; }
	 * 
	 * public void setCompletedDate(String completedDate) { this.completedDate =
	 * completedDate; }
	 */

	public int getCreatedOrModifiedBy() {
		return createdOrModifiedBy;
	}

	public void setCreatedOrModifiedBy(int createdOrModifiedBy) {
		this.createdOrModifiedBy = createdOrModifiedBy;
	}

}
