package com.rainier.beans;

public class FetchAddEmpleaveRequestBean {
	private int id;
	private String firstName;
	private String lastname;
	private String employeeId;
	private  int assignedLeaves;
	private int usedLeaves;
	private int leaveBalance;
	private int allottedYear;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public int getAssignedLeaves() {
		return assignedLeaves;
	}
	public void setAssignedLeaves(int assignedLeaves) {
		this.assignedLeaves = assignedLeaves;
	}
	public int getUsedLeaves() {
		return usedLeaves;
	}
	public void setUsedLeaves(int usedLeaves) {
		this.usedLeaves = usedLeaves;
	}
	public int getLeaveBalance() {
		return leaveBalance;
	}
	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	public int getAllottedYear() {
		return allottedYear;
	}
	public void setAllottedYear(int allottedYear) {
		this.allottedYear = allottedYear;
	}
	
	

}
