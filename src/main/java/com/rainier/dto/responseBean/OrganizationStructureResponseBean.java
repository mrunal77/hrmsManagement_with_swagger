package com.rainier.dto.responseBean;

import com.rainier.entities.Main_Businessunits;

import java.util.List;

public class OrganizationStructureResponseBean {
	private String name;
	private String designation;
	private List<Main_Businessunits> subordinates;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<Main_Businessunits> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Main_Businessunits> subordinates) {
		this.subordinates = subordinates;
	}

}
