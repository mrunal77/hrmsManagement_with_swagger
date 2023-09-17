package com.rainier.response;

import java.math.BigInteger;

public class TimeSheetStatusCountResponse {
	private BigInteger all;
	private BigInteger approved;
	private BigInteger rejected;
	private BigInteger saved;
	private BigInteger submitted;
	private short calWeek;
	
	private int year;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public BigInteger getAll() {
		return all;
	}
	public void setAll(BigInteger all) {
		this.all = all;
	}
	public BigInteger getApproved() {
		return approved;
	}
	public void setApproved(BigInteger approved) {
		this.approved = approved;
	}
	public BigInteger getRejected() {
		return rejected;
	}
	public void setRejected(BigInteger rejected) {
		this.rejected = rejected;
	}
	public BigInteger getSaved() {
		return saved;
	}
	public void setSaved(BigInteger saved) {
		this.saved = saved;
	}
	public BigInteger getSubmitted() {
		return submitted;
	}
	public void setSubmitted(BigInteger submitted) {
		this.submitted = submitted;
	}
	public short getCalWeek() {
		return calWeek;
	}
	public void setCalWeek(short calWeek) {
		this.calWeek = calWeek;
	}
	
	
}
