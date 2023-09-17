package com.rainier.beans;

import java.sql.Date;

public class SkillsBean {
	private int id;
	private Integer userId;
	private String skillName;
	private int yearsOfExp;
	private Integer competencyLevelId;
	private Date yearSkillLastUsed;

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

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getYearsOfExp() {
		return yearsOfExp;
	}

	public void setYearsOfExp(int yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}

	public Integer getCompetencyLevelId() {
		return competencyLevelId;
	}

	public void setCompetencyLevelId(Integer competencyLevelId) {
		this.competencyLevelId = competencyLevelId;
	}

	public Date getYearSkillLastUsed() {
		return yearSkillLastUsed;
	}

	public void setYearSkillLastUsed(Date yearSkillLastUsed) {
		this.yearSkillLastUsed = yearSkillLastUsed;
	}

}
