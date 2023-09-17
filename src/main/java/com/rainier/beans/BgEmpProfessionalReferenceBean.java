package com.rainier.beans;

import java.util.List;

public class BgEmpProfessionalReferenceBean {
	
	private int id;
	private int userId;
	
	private List<BgReferenceApplied> applied;

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

	public List<BgReferenceApplied> getApplied() {
		return applied;
	}

	public void setApplied(List<BgReferenceApplied> applied) {
		this.applied = applied;
	}
	
	
	
	
	
}
