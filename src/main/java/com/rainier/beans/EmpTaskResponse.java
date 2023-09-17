package com.rainier.beans;

import java.util.Date;

public class EmpTaskResponse {
private Date date;
private String taskName;
private int empId;
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
public int getEmpId() {
	return empId;
}
public void setEmpId(int empId) {
	this.empId = empId;
}

}
