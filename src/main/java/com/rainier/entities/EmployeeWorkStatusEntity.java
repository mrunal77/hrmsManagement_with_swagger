package com.rainier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_work_status_entity")
public class EmployeeWorkStatusEntity implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="emp_id")
	private int empId;
	@Column(name = "status")
	private String status;
	@Column(name="cal_week")
	private short calweek;
	@Column(name="rep_id")
	private Integer repId;
	@Column(name = "ts_year")
	private int year;
	@Column(name = "ts_month")
	private Short month;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Short getMonth() {
		return month;
	}
	public void setMonth(Short month) {
		this.month = month;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public short getCalweek() {
		return calweek;
	}
	public void setCalweek(short calweek) {
		this.calweek = calweek;
	}
	public Integer getRepId() {
		return repId;
	}
	public void setRepId(Integer repId) {
		this.repId = repId;
	}
	
	}
	
	
	


