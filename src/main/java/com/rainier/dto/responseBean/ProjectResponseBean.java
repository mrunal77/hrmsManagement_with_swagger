package com.rainier.dto.responseBean;

import java.util.Date;

public class ProjectResponseBean {
private int projectId;
private String projectName;
private Object projectStatus;
private int baseProject;
private String description;
private int clientId;
private int currencyId;
private Object projectType;
private int leadApproveTs;
private int est_hours;
private Date startDate;
private Date endDate;

private String initiatedDate;
private String hold_date;
private String completeDate;

private String message;
private Boolean status;

//setters and getters

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

public Object getProjectStatus() {
	return projectStatus;
}
public void setProjectStatus(Object projectStatus) {
	this.projectStatus = projectStatus;
}
public int getBaseProject() {
	return baseProject;
}
public void setBaseProject(int baseProject) {
	this.baseProject = baseProject;
}
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

public Object getProjectType() {
	return projectType;
}
public void setProjectType(Object projectType) {
	this.projectType = projectType;
}
public int getLeadApproveTs() {
	return leadApproveTs;
}
public void setLeadApproveTs(int leadApproveTs) {
	this.leadApproveTs = leadApproveTs;
}
public int getEst_hours() {
	return est_hours;
}
public void setEst_hours(int est_hours) {
	this.est_hours = est_hours;
}
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
public String getInitiatedDate() {
	return initiatedDate;
}
public void setInitiatedDate(String initiatedDate) {
	this.initiatedDate = initiatedDate;
}
public String getHold_date() {
	return hold_date;
}
public void setHold_date(String hold_date) {
	this.hold_date = hold_date;
}
public String getCompleteDate() {
	return completeDate;
}
public void setCompleteDate(String completeDate) {
	this.completeDate = completeDate;
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
