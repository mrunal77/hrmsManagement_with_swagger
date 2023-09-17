package com.rainier.dto.responseBean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class OrganizationHierarchyResponseBean {
	private int id;
	private int userId;
	private String name;
	private String img;
	private String designation;
	private List<Object> subordinates;
	@JsonIgnore
	private int reportingManagerId;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<Object> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Object> subordinates) {
		this.subordinates = subordinates;
	}

	public int getReportingManagerId() {
		return reportingManagerId;
	}

	public void setReportingManagerId(int reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}

}
