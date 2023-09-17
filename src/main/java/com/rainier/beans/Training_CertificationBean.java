package com.rainier.beans;

import java.sql.Timestamp;


public class Training_CertificationBean {
	
	private int id;
	private Integer userId;
	private String courseName;
	private String description;
	private String courselevel;
	private String courseOfferedBy;
	private String certificationName;
	private Timestamp issuedDate;
	private int isactive;

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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCourseOfferedBy() {
		return courseOfferedBy;
	}

	public void setCourseOfferedBy(String courseOfferedBy) {
		this.courseOfferedBy = courseOfferedBy;
	}

	public String getCertificationName() {
		return certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	public Timestamp getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Timestamp issuedDate) {
		this.issuedDate = issuedDate;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public String getCourselevel() {
		return courselevel;
	}

	public void setCourselevel(String courselevel) {
		this.courselevel = courselevel;
	}
	

}
