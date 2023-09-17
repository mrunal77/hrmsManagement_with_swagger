package com.rainier.beans;

public class NationalityAddBean {
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String nationalitycontext;
	private String description;
	
	public String getNationalitycontext() {
		return nationalitycontext;
	}
	
	public void setNationalitycontext(String nationalitycontext) {
		this.nationalitycontext = nationalitycontext;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	

}
