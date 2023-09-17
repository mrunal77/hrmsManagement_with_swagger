package com.rainier.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_userloginlog")
public class UserLoginLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "userid")
	private Integer userId;

	@Column(name = "emailaddress")
	private String email;

	@Column(name = "emprole")
	private int emproleId;

	@Column(name = "employeeId")
	private String employeeId;

	@Column(name = "userfullname")
	private String userfullName;

	@Column(name = "logindatetime")
	private Timestamp logindatetime;

	@Column(name = "empipaddress")
	private String empipAddress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmproleId() {
		return emproleId;
	}

	public void setEmproleId(int emproleId) {
		this.emproleId = emproleId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Object object) {
		this.employeeId = (String) object;
	}

	public String getUserfullName() {
		return userfullName;
	}

	public void setUserfullName(String userfullName) {
		this.userfullName = userfullName;
	}

	public Timestamp getLogindatetime() {
		return logindatetime;
	}

	public void setLogindatetime(Timestamp logindatetime) {
		this.logindatetime = logindatetime;
	}

	public String getEmpipAddress() {
		return empipAddress;
	}

	public void setEmpipAddress(String empipAddress) {
		this.empipAddress = empipAddress;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
