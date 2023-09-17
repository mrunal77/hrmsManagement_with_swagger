package com.rainier.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="rs_BgEmpEmploymentGap")

public class BgEmpEmploymentGapEntity {
	
	@Id
	@GeneratedValue

	@Column(name="id")
	private int id;
	@Column(name="user_id")
	private int userId;
	@Temporal(TemporalType.DATE)
	@Column(name="from_date")
	private Date fromDate;
	@Temporal(TemporalType.DATE)
	@Column(name="to_date")
	private Date toDate;
	@Column(name="reason_for_Employment")
	private String reasonForEmployment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getReasonForEmployment() {
		return reasonForEmployment;
	}
	public void setReasonForEmployment(String reasonForEmployment) {
		this.reasonForEmployment = reasonForEmployment;
	}
	
	

}
