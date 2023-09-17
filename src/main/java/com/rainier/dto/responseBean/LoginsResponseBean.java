package com.rainier.dto.responseBean;

import java.sql.Timestamp;

public class LoginsResponseBean {
	private String employeeId;
	private int emproleId;
	private String email;
	private String userfullName;
	private Integer userId;
	private Timestamp logindatetime;
    private int repId;
    
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getEmproleId() {
		return emproleId;
	}

	public void setEmproleId(int emproleId) {
		this.emproleId = emproleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserfullName() {
		return userfullName;
	}

	public void setUserfullName(String userfullName) {
		this.userfullName = userfullName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getLogindatetime() {
		return logindatetime;
	}

	public void setLogindatetime(Timestamp logindatetime) {
		this.logindatetime = logindatetime;
	}

	public int getRepId() {
		return repId;
	}

	public void setRepId(int repId) {
		this.repId = repId;
	}
	

}
